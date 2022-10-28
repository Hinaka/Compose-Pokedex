/*
 * Copyright 2022 Hinaka (Trung Nguyễn Minh Trần)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.hinaka.pokedex.feature.pokemon.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.pokemon.PokemonDeprecated
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.feature.pokemon.usecase.GetPokemonDamageRelationStreamUseCase
import dev.hinaka.pokedex.feature.pokemon.usecase.GetPokemonDetailsStreamUseCase
import dev.hinaka.pokedex.feature.pokemon.usecase.GetPokemonPagingStreamUseCase
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

private const val SELECTED_POKEMON_IDS = "selectedPokemonIds"
private const val SEARCH_QUERY = "searchQuery"

@HiltViewModel
class PokemonViewModel @Inject constructor(
    getPokemonPagingStreamUseCase: GetPokemonPagingStreamUseCase,
    getPokemonDetailsStreamUseCase: GetPokemonDetailsStreamUseCase,
    @Named("previous") getPreviousPokemonDetailsStreamUseCase: GetPokemonDetailsStreamUseCase,
    @Named("next") getNextPokemonDetailsStreamUseCase: GetPokemonDetailsStreamUseCase,
    getPokemonDamageRelationStreamUseCase: GetPokemonDamageRelationStreamUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val selectedPokemonIds = savedStateHandle
        .getStateFlow<Array<Int>>(SELECTED_POKEMON_IDS, emptyArray())

    private val selectedPokemonId =
        selectedPokemonIds.map { ids -> ids.lastOrNull()?.let { Id(it) } }

    private val searchQuery = savedStateHandle
        .getStateFlow(SEARCH_QUERY, "")

    private val pokemonPagingFlow = searchQuery.mapLatest {
        getPokemonPagingStreamUseCase(10).cachedIn(viewModelScope)
    }

    private val selectedPokemonDeprecated: Flow<PokemonDeprecated?> = selectedPokemonId.flatMapLatest {
        it?.let {
            getPokemonDetailsStreamUseCase(it)
        } ?: flow {
            emit(null)
        }
    }

    private val previousPokemonDeprecated: Flow<PokemonDeprecated?> = selectedPokemonId.flatMapLatest {
        it?.let {
            getPreviousPokemonDetailsStreamUseCase(it)
        } ?: flow {
            emit(null)
        }
    }

    private val nextPokemonDeprecated: Flow<PokemonDeprecated?> = selectedPokemonId.flatMapLatest {
        it?.let {
            getNextPokemonDetailsStreamUseCase(it)
        } ?: flow {
            emit(null)
        }
    }

    private val damageRelation: Flow<Map<Type, DamageFactor>> = selectedPokemonDeprecated.flatMapLatest {
        it?.let {
            getPokemonDamageRelationStreamUseCase(it.types.first(), it.types.getOrNull(1))
        } ?: flow {
            emit(emptyMap())
        }
    }

    val uiState: StateFlow<PokemonUiState> = combine(
        pokemonPagingFlow,
        selectedPokemonDeprecated,
        previousPokemonDeprecated,
        nextPokemonDeprecated,
        damageRelation
    ) { pokemonPagingFlow, selectedPokemon, previousPokemon, nextPokemon, damageRelation ->
        PokemonUiState(
            pokemonDeprecatedPagingFlow = pokemonPagingFlow,
            selectedPokemonDeprecated = selectedPokemon,
            previousPokemonDeprecated = previousPokemon,
            nextPokemonDeprecated = nextPokemon,
            damageRelation = damageRelation
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PokemonUiState()
    )

    fun selectPokemon(pokemonDeprecated: PokemonDeprecated) {
        val currentIds = selectedPokemonIds.value
        savedStateHandle[SELECTED_POKEMON_IDS] = currentIds + pokemonDeprecated.id.value
    }

    fun unselectPokemon() {
        val currentIds = selectedPokemonIds.value
        savedStateHandle[SELECTED_POKEMON_IDS] = currentIds.dropLast(1).toTypedArray()
    }

    fun clearSelectedPokemons() {
        savedStateHandle[SELECTED_POKEMON_IDS] = emptyArray<Int>()
    }
}

data class PokemonUiState(
    val pokemonDeprecatedPagingFlow: Flow<PagingData<PokemonDeprecated>> = emptyFlow(),
    val selectedPokemonDeprecated: PokemonDeprecated? = null,
    val previousPokemonDeprecated: PokemonDeprecated? = null,
    val nextPokemonDeprecated: PokemonDeprecated? = null,
    val damageRelation: Map<Type, DamageFactor> = emptyMap()
)

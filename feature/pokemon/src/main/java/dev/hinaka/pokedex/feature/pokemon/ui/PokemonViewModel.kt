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

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.feature.pokemon.usecase.GetPokemonDetailsStreamUseCase
import dev.hinaka.pokedex.feature.pokemon.usecase.GetPokemonPagingStreamUseCase
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
import javax.inject.Inject
import javax.inject.Named

private const val SELECTED_POKEMON_ID = "selectedPokemonId"
private const val SEARCH_QUERY = "searchQuery"

@HiltViewModel
class PokemonViewModel @Inject constructor(
    getPokemonPagingStreamUseCase: GetPokemonPagingStreamUseCase,
    getPokemonDetailsStreamUseCase: GetPokemonDetailsStreamUseCase,
    @Named("previous") getPreviousPokemonDetailsStreamUseCase: GetPokemonDetailsStreamUseCase,
    @Named("next") getNextPokemonDetailsStreamUseCase: GetPokemonDetailsStreamUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val selectedPokemonId = savedStateHandle
        .getStateFlow<Int?>(SELECTED_POKEMON_ID, null)
        .map { it?.let { Id(it) } }

    private val searchQuery = savedStateHandle
        .getStateFlow(SEARCH_QUERY, "")

    private val pokemonPagingFlow = searchQuery.mapLatest {
        getPokemonPagingStreamUseCase(10).cachedIn(viewModelScope)
    }

    private val selectedPokemon: Flow<Pokemon?> = selectedPokemonId.flatMapLatest {
        it?.let {
            getPokemonDetailsStreamUseCase(it)
        } ?: flow<Pokemon?> {
            emit(null)
        }
    }

    private val previousPokemon: Flow<Pokemon?> = selectedPokemonId.flatMapLatest {
        it?.let {
            getPreviousPokemonDetailsStreamUseCase(it)
        } ?: flow {
            emit(null)
        }
    }

    private val nextPokemon: Flow<Pokemon?> = selectedPokemonId.flatMapLatest {
        it?.let {
            getNextPokemonDetailsStreamUseCase(it)
        } ?: flow {
            emit(null)
        }
    }

    val uiState: StateFlow<PokemonUiState> = combine(
        pokemonPagingFlow,
        selectedPokemon,
        previousPokemon,
        nextPokemon
    ) { pokemonPagingFlow, selectedPokemon, previousPokemon, nextPokemon ->
        Log.d("Trung", "previous = ${previousPokemon?.name}")
        Log.d("Trung", "next = ${nextPokemon?.name}")
        PokemonUiState(
            pokemonPagingFlow = pokemonPagingFlow,
            selectedPokemon = selectedPokemon
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PokemonUiState()
    )

    fun selectPokemon(pokemon: Pokemon) {
        savedStateHandle[SELECTED_POKEMON_ID] = pokemon.id.value
    }

    fun unselectPokemon() {
        savedStateHandle[SELECTED_POKEMON_ID] = null
    }
}

data class PokemonUiState(
    val pokemonPagingFlow: Flow<PagingData<Pokemon>> = emptyFlow(),
    val selectedPokemon: Pokemon? = null,
)

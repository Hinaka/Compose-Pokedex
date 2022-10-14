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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    getPokemonPagingStreamUseCase: GetPokemonPagingStreamUseCase,
    getPokemonDetailsStreamUseCase: GetPokemonDetailsStreamUseCase
) : ViewModel() {

    private val pokemonPagingFlow = flow {
        emit(getPokemonPagingStreamUseCase(10).cachedIn(viewModelScope))
    }

    private val selectedPokemonId = MutableStateFlow<Id?>(null)

    private val selectedPokemon: Flow<Pokemon?> = selectedPokemonId.flatMapLatest {
        it?.let {
            getPokemonDetailsStreamUseCase(it)
        } ?: flow<Pokemon?> {
            emit(null)
        }
    }

    val uiState: StateFlow<PokemonScreenUiState> = combine(
        pokemonPagingFlow,
        selectedPokemon
    ) { pokemonPagingFlow, selectedPokemon ->
        Log.d("Trung", "selectedPokemon = $selectedPokemon")
        PokemonScreenUiState(
            pokemonPagingFlow = pokemonPagingFlow,
            selectedPokemon = selectedPokemon
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PokemonScreenUiState()
    )

    fun selectPokemon(pokemon: Pokemon) {
        selectedPokemonId.update { pokemon.id }
    }

    fun unselectPokemon() {
        selectedPokemonId.update { null }
    }
}

data class PokemonScreenUiState(
    val pokemonPagingFlow: Flow<PagingData<Pokemon>> = emptyFlow(),
    val selectedPokemon: Pokemon? = null
)

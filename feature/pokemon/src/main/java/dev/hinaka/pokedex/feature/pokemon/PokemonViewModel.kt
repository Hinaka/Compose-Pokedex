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
package dev.hinaka.pokedex.feature.pokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.feature.pokemon.usecase.GetPokemonPagingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    getPokemonPagingUseCase: GetPokemonPagingUseCase
) : ViewModel() {

    private val pokemonPagingFlow = flow {
        emit(getPokemonPagingUseCase(10).cachedIn(viewModelScope))
    }

    private val selectedPokemon = MutableStateFlow<Pokemon?>(null)

    val uiState: StateFlow<PokemonScreenUiState> = combine(
        pokemonPagingFlow,
        selectedPokemon
    ) { pokemonPagingFlow, selectedPokemon ->
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
        selectedPokemon.update {
            pokemon
        }
    }

    fun unselectPokemon() {
        selectedPokemon.update { null }
    }
}

data class PokemonScreenUiState(
    val pokemonPagingFlow: Flow<PagingData<Pokemon>> = emptyFlow(),
    val selectedPokemon: Pokemon? = null
)

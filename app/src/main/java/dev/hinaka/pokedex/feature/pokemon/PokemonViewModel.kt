package dev.hinaka.pokedex.feature.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.feature.pokemon.usecase.GetPokemonsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    val uiState = flow {
        emitAll(getPokemonsUseCase())
    }.map {
        PokemonScreenUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PokemonScreenUiState()
    )
}

data class PokemonScreenUiState(
    val pokemons: List<Pokemon> = emptyList()
)
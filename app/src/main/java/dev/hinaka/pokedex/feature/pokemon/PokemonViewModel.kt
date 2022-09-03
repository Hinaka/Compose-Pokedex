package dev.hinaka.pokedex.feature.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.domain.Stats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(PokemonScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.emit(
                PokemonScreenUiState(
                    pokemons = (1..10).map {
                        Pokemon(
                            id = Id(it),
                            name = "name $it",
                            types = emptyList(),
                            imageUrl = "Image $it",
                            abilities = emptyList(),
                            baseStats = Stats(0, 0, 0, 0, 0, 0),
                            baseMoves = emptyList(),
                        )
                    }
                )
            )
        }
    }
}

data class PokemonScreenUiState(
    val pokemons: List<Pokemon> = emptyList()
)
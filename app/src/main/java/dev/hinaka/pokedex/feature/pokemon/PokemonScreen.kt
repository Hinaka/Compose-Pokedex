package dev.hinaka.pokedex.feature.pokemon

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.hinaka.pokedex.domain.Pokemon

@Composable
fun PokemonRoute(
    modifier: Modifier = Modifier,
    pokemonViewModel: PokemonViewModel = viewModel(),
) {
    val uiState by pokemonViewModel.uiState.collectAsState()

    PokemonScreen(
        pokemons = uiState.pokemons,
        modifier = modifier,
    )
}

@Composable
fun PokemonScreen(
    pokemons: List<Pokemon>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(pokemons) { pokemon ->
            Text(text = pokemon.name)
        }
    }
}
package dev.hinaka.pokedex.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.hinaka.pokedex.feature.pokemon.PokemonRoute
import dev.hinaka.pokedex.ui.theme.PokedexTheme

@Composable
fun PokedexApp() {
    PokedexTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            PokemonRoute()
        }
    }
}
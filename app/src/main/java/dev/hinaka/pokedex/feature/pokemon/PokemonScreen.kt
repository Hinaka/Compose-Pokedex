package dev.hinaka.pokedex.feature.pokemon

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PokemonRoute(
    modifier: Modifier = Modifier
) {
    PokemonScreen()
}

@Composable
fun PokemonScreen(
    modifier: Modifier = Modifier
) {
    Text(text = "Pokemon Screen")
}
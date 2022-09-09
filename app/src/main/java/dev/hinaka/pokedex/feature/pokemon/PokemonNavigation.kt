package dev.hinaka.pokedex.feature.pokemon

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.pokemonGraph(
    baseRoute: String,
) {
    composable(baseRoute) {
        PokemonRoute()
    }
}
package dev.hinaka.pokedex.feature.pokemon.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.hinaka.pokedex.feature.pokemon.PokemonRoute

fun NavGraphBuilder.pokemonGraph(
    baseRoute: String,
) {
    composable(baseRoute) {
        PokemonRoute()
    }
}
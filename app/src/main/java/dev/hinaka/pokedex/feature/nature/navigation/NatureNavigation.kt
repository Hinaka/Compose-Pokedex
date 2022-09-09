package dev.hinaka.pokedex.feature.nature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.hinaka.pokedex.feature.nature.NatureRoute

fun NavGraphBuilder.natureGraph(
    baseRoute: String
) {
    composable(baseRoute) {
        NatureRoute()
    }
}
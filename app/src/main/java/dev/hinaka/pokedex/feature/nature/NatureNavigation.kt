package dev.hinaka.pokedex.feature.nature

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.natureGraph(
    baseRoute: String
) {
    composable(baseRoute) {
        NatureRoute()
    }
}
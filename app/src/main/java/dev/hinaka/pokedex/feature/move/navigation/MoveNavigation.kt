package dev.hinaka.pokedex.feature.move.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.hinaka.pokedex.feature.move.MoveRoute

fun NavGraphBuilder.moveGraph(
    baseRoute: String,
) {
    composable(baseRoute) {
        MoveRoute()
    }
}
package dev.hinaka.pokedex.feature.move

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.moveGraph(
    baseRoute: String,
) {
    composable(baseRoute) {
        MoveRoute()
    }
}
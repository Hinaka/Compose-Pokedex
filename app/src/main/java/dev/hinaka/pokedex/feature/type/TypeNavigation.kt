package dev.hinaka.pokedex.feature.type

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.typeGraph(
    baseRoute: String,
) {
    composable(baseRoute) {
        TypeRoute()
    }
}
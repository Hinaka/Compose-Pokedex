package dev.hinaka.pokedex.feature.type.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.hinaka.pokedex.feature.type.TypeRoute

fun NavGraphBuilder.typeGraph(
    baseRoute: String,
) {
    composable(baseRoute) {
        TypeRoute()
    }
}
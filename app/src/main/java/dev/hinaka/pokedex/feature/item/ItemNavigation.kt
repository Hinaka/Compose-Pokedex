package dev.hinaka.pokedex.feature.item

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.itemGraph(
    baseRoute: String,
) {
    composable(baseRoute) {
        ItemRoute()
    }
}
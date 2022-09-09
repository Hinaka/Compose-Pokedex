package dev.hinaka.pokedex.feature.item.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.hinaka.pokedex.feature.item.ItemRoute

fun NavGraphBuilder.itemGraph(
    baseRoute: String,
) {
    composable(baseRoute) {
        ItemRoute()
    }
}
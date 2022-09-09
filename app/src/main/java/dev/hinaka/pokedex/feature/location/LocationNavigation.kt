package dev.hinaka.pokedex.feature.location

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.locationGraph(
    baseRoute: String
) {
    composable(baseRoute) {
        LocationRoute()
    }
}
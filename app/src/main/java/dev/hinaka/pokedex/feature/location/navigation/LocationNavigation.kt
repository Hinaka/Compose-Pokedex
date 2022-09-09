package dev.hinaka.pokedex.feature.location.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.hinaka.pokedex.feature.location.LocationRoute

fun NavGraphBuilder.locationGraph(
    baseRoute: String
) {
    composable(baseRoute) {
        LocationRoute()
    }
}
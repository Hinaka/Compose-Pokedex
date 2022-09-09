package dev.hinaka.pokedex.feature.ability

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.abilityGraph(
    baseRoute: String,
) {
    composable(baseRoute) {
        AbilityRoute()
    }
}
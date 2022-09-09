package dev.hinaka.pokedex.feature.ability.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.hinaka.pokedex.feature.ability.AbilityRoute

fun NavGraphBuilder.abilityGraph(
    baseRoute: String,
) {
    composable(baseRoute) {
        AbilityRoute()
    }
}
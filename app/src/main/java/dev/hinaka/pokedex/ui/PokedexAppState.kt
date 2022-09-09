package dev.hinaka.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.hinaka.pokedex.navigation.TopLevelDestination

@Composable
fun rememberPokedexAppState(
    navController: NavHostController = rememberNavController(),
): PokedexAppState {
    return remember(navController) {
        PokedexAppState(navController)
    }
}

@Stable
class PokedexAppState(
    val navController: NavHostController,
) {
    val currentDestination
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations = TopLevelDestination.values().asList()

    fun navigate(destination: TopLevelDestination) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id)
        }
    }
}
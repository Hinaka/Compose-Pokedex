package dev.hinaka.pokedex.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import dev.hinaka.pokedex.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexDrawer(
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    closeDrawer: () -> Unit,
) {
    destinations.forEach { destination ->
        val selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = destination.labelId)) },
            selected = selected,
            onClick = {
                onNavigateToDestination(destination)
                closeDrawer()
            })
    }
}
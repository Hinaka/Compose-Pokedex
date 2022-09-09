/*
 * Copyright 2022 Hinaka (Trung Nguyễn Minh Trần)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.hinaka.pokedex.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.navigation.PokedexNavHost
import dev.hinaka.pokedex.navigation.TopLevelDestination
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexApp(
    appState: PokedexAppState = rememberPokedexAppState(),
) {
    PokedexTheme {
        PokedexDrawer(
            destinations = appState.topLevelDestinations,
            onNavigateToDestination = appState::navigate,
            currentDestination = appState.currentDestination,
        ) {
            Scaffold(
                topBar = {
                    SmallTopAppBar(
                        title = {
                            Text(text = "Pokedex")
                        }
                    )
                },
            ) { contentPadding ->
                PokedexNavHost(
                    navController = appState.navController,
                    modifier = Modifier.padding(contentPadding)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexDrawer(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            destinations.forEach { destination ->
                val selected =
                    currentDestination?.hierarchy?.any { it.route == destination.route } == true
                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = destination.labelId)) },
                    selected = selected,
                    onClick = {
                        scope.launch { drawerState.close() }
                        onNavigateToDestination(destination)
                    }
                )
            }
        }
    ) {
        content()
    }
}

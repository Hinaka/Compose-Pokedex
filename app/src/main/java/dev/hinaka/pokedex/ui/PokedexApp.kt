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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.feature.ability.AbilityRoute
import dev.hinaka.pokedex.feature.item.ItemRoute
import dev.hinaka.pokedex.feature.location.LocationRoute
import dev.hinaka.pokedex.feature.move.MoveRoute
import dev.hinaka.pokedex.feature.nature.NatureRoute
import dev.hinaka.pokedex.feature.pokemon.PokemonRoute
import dev.hinaka.pokedex.feature.type.TypeRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexApp() {
    PokedexTheme {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = Closed)
        val scope = rememberCoroutineScope()
        val topLevelDestinations = listOf(
            TopLevelDestination("pokemon", "Pokedex"),
            TopLevelDestination("move", "Move Dex"),
            TopLevelDestination("ability", "Ability Dex"),
            TopLevelDestination("item", "Item Dex"),
            TopLevelDestination("location", "Location Dex"),
            TopLevelDestination("type", "Type Dex"),
            TopLevelDestination("nature", "Nature Dex"),
        )

        val selectedRoute = navController.currentDestination?.route.orEmpty()
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                topLevelDestinations.forEach { destination ->
                    NavigationDrawerItem(
                        label = { Text(text = destination.displayName) },
                        selected = destination.route == selectedRoute,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(destination.route)
                        }
                    )
                }
            }
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
                NavHost(
                    navController = navController,
                    startDestination = "pokemon",
                    modifier = Modifier.padding(contentPadding)
                ) {
                    composable("pokemon") {
                        PokemonRoute()
                    }

                    composable("move") {
                        MoveRoute()
                    }

                    composable("ability") {
                        AbilityRoute()
                    }

                    composable("item") {
                        ItemRoute()
                    }

                    composable("location") {
                        LocationRoute()
                    }

                    composable("type") {
                        TypeRoute()
                    }

                    composable("nature") {
                        NatureRoute()
                    }
                }
            }
        }
    }
}

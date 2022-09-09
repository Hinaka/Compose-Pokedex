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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.feature.pokemon.PokemonRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexApp() {
    PokedexTheme {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = Closed)
        val scope = rememberCoroutineScope()
        val items = listOf(
            "Pokedex",
            "Move Dex",
            "Ability Dex",
            "Item Dex",
            "Location Dex",
            "Type Dex",
            "Nature Dex",
        )
        var selectedItem by remember { mutableStateOf(items.first()) }
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                items.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(text = item) },
                        selected = item == selectedItem,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem = item
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
                }
            }
        }
    }
}

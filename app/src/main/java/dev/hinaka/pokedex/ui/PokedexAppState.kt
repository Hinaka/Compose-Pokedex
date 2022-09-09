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
    navController: NavHostController = rememberNavController()
): PokedexAppState {
    return remember(navController) {
        PokedexAppState(navController)
    }
}

@Stable
class PokedexAppState(
    val navController: NavHostController
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

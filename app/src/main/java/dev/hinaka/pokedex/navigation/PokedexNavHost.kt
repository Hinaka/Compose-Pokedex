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
package dev.hinaka.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.hinaka.pokedex.feature.ability.navigation.abilityGraph
import dev.hinaka.pokedex.feature.item.navigation.itemGraph
import dev.hinaka.pokedex.feature.location.navigation.locationGraph
import dev.hinaka.pokedex.feature.move.navigation.moveGraph
import dev.hinaka.pokedex.feature.nature.navigation.natureGraph
import dev.hinaka.pokedex.feature.pokemon.navigation.pokemonGraph
import dev.hinaka.pokedex.feature.type.navigation.typeGraph
import dev.hinaka.pokedex.navigation.TopLevelDestination.Ability
import dev.hinaka.pokedex.navigation.TopLevelDestination.Item
import dev.hinaka.pokedex.navigation.TopLevelDestination.Location
import dev.hinaka.pokedex.navigation.TopLevelDestination.Move
import dev.hinaka.pokedex.navigation.TopLevelDestination.Nature
import dev.hinaka.pokedex.navigation.TopLevelDestination.Pokemon
import dev.hinaka.pokedex.navigation.TopLevelDestination.Type

@Composable
fun PokedexNavHost(
    navController: NavHostController,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: TopLevelDestination = Pokemon
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        pokemonGraph(
            baseRoute = Pokemon.route,
            openDrawer = openDrawer
        )
        moveGraph(
            baseRoute = Move.route,
            openDrawer = openDrawer
        )
        abilityGraph(Ability.route)
        itemGraph(Item.route)
        locationGraph(Location.route)
        typeGraph(Type.route)
        natureGraph(Nature.route)
    }
}

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
package dev.hinaka.pokedex.feature.pokemon.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.feature.pokemon.ui.DetailsTab.INFO
import dev.hinaka.pokedex.feature.pokemon.ui.DetailsTab.MENU
import dev.hinaka.pokedex.feature.pokemon.ui.DetailsTab.MORE
import dev.hinaka.pokedex.feature.pokemon.ui.DetailsTab.MOVES

@Composable
fun PokemonDetails(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    var selectedIndex by remember { mutableStateOf(0) }
    Column(modifier = modifier.padding(contentPadding)) {
        PokemonCard(pokemon = pokemon)
        Body(
            tab = DetailsTab.values()[selectedIndex],
            modifier = Modifier.weight(1f)
        )
        TabView(
            selectedIndex = selectedIndex,
            onTabChanged = { selectedIndex = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
    }
}

@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    Text(text = "Pokemon Card")
}

@Composable
private fun Body(
    tab: DetailsTab,
    modifier: Modifier = Modifier,
) {
    when (tab) {
        INFO -> PokemonInfo(
            modifier = modifier,
        )
        MOVES -> PokemonMoves(
            modifier = modifier,
        )
        MORE -> PokemonMore(
            modifier = modifier,
        )
        MENU -> PokemonMenu(
            modifier = modifier
        )
    }
}

@Composable
private fun PokemonInfo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Info")
    }
}

@Composable
fun PokemonMoves(
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Moves")
    }
}

@Composable
fun PokemonMore(
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "More")
    }
}

@Composable
fun PokemonMenu(
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Menu")
    }
}

@Composable
fun TabView(
    selectedIndex: Int,
    onTabChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    TabRow(selectedTabIndex = selectedIndex) {
        DetailsTab.values().mapIndexed { index, tab ->
            Tab(selected = index == selectedIndex, onClick = { onTabChanged(index) }) {
                Text(text = tab.displayName)
            }
        }
    }
}

private enum class DetailsTab {
    INFO,
    MOVES,
    MORE,
    MENU
}

private val DetailsTab.displayName
    get() = when (this) {
        INFO -> "Info"
        MOVES -> "Moves"
        MORE -> "More"
        MENU -> "Menu"
    }
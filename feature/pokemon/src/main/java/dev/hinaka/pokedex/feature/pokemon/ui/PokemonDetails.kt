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
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.ui.type.onTypeContainerColor
import dev.hinaka.pokedex.core.ui.type.typeContainerColor
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
    Column(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = 8.dp)
    ) {
        PokemonCard(
            pokemon = pokemon,
            modifier = Modifier.fillMaxWidth()
        )
        TabContent(
            tab = DetailsTab.values()[selectedIndex],
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        TabRowMenu(
            selectedIndex = selectedIndex,
            onTabChanged = { selectedIndex = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = pokemon.types.first().typeContainerColor,
            contentColor = pokemon.types.first().onTypeContainerColor,
        )
    ) {
        Row(
            modifier = Modifier
                .height(Min)
                .padding(start = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PokemonName(name = pokemon.name, modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(4.dp))
                    PokemonId(id = pokemon.id)
                }
                PokemonTypes(
                    types = pokemon.types,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            PokemonImage(
                imageUrl = pokemon.imageUrl,
                imageDescription = "Image of ${pokemon.name}",
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}

@Composable
private fun TabContent(
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
        MORE -> PokemonExtraInfo(
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
fun PokemonExtraInfo(
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
fun TabRowMenu(
    selectedIndex: Int,
    onTabChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier
    ) {
        DetailsTab.values().mapIndexed { index, tab ->
            val selected = index == selectedIndex
            Tab(selected = selected, onClick = { onTabChanged(index) }) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(48.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = "Icon of tab ${tab.displayName}",
                    )
                    if (selected) {
                        Text(
                            text = tab.displayName,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }
    }
}

private enum class DetailsTab(
    val icon: ImageVector,
    val displayName: String,
) {
    INFO(
        icon = Filled.Info,
        displayName = "Info",
    ),
    MOVES(
        icon = Filled.Edit,
        displayName = "Moves"
    ),
    MORE(
        icon = Filled.Add,
        displayName = "More"
    ),
    MENU(
        icon = Filled.Menu,
        displayName = "Menu"
    )
}
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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.hinaka.pokedex.core.ui.pokemon.PokemonId
import dev.hinaka.pokedex.core.ui.pokemon.PokemonName
import dev.hinaka.pokedex.core.ui.type.PokemonTypes
import dev.hinaka.pokedex.core.ui.type.onTypeContainerColor
import dev.hinaka.pokedex.core.ui.type.typeContainerColor
import dev.hinaka.pokedex.core.ui.utils.spacer
import dev.hinaka.pokedex.domain.EmptyAbility
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.feature.pokemon.R.drawable
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
    val containerColor = pokemon.types.first().typeContainerColor
    val contentColor = pokemon.types.first().onTypeContainerColor

    Column(
        modifier = modifier
            .padding(contentPadding)
    ) {
        PokemonCard(
            pokemon = pokemon,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
        TabContent(
            tab = DetailsTab.values()[selectedIndex],
            pokemon = pokemon,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 8.dp)
        )
        TabRowMenu(
            selectedIndex = selectedIndex,
            onTabChanged = { selectedIndex = it },
            modifier = Modifier.fillMaxWidth(),
            containerColor = containerColor,
            contentColor = contentColor
        )
    }
}

@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f),
            contentColor = pokemon.types.first().onTypeContainerColor
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
                    PokemonName(
                        name = pokemon.name,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    PokemonId(
                        id = pokemon.id,
                        style = MaterialTheme.typography.headlineSmall
                    )
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
private fun PokemonImage(
    imageUrl: String,
    imageDescription: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f),
        shape = RoundedCornerShape(
            topStartPercent = 50,
            bottomStartPercent = 50
        )
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = imageDescription,
            placeholder = painterResource(id = drawable.ic_pokeball),
            modifier = Modifier
                .defaultMinSize(minHeight = 120.dp)
                .padding(start = 16.dp)
        )
    }
}

@Composable
private fun TabContent(
    tab: DetailsTab,
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    when (tab) {
        INFO -> PokemonInfo(
            pokemon = pokemon,
            modifier = modifier
        )
        MOVES -> PokemonMoves(
            modifier = modifier
        )
        MORE -> PokemonExtraInfo(
            modifier = modifier
        )
        MENU -> PokemonMenu(
            modifier = modifier
        )
    }
}

@Composable
private fun PokemonInfo(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        val borderStroke = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        spacer(dp = 16.dp)
        Text(
            text = "Species",
            modifier = Modifier.align(CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )
        spacer(dp = 8.dp)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = pokemon.flavorText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            border = borderStroke,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(vertical = 8.dp, horizontal = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                spacer(dp = 4.dp)
                Text(
                    text = "Pokedex entry",
                    modifier = Modifier.align(CenterHorizontally),
                    style = MaterialTheme.typography.labelMedium
                )
                spacer(dp = 16.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = CenterHorizontally,
                    ) {
                        Text(
                            text = "%.2f".format(pokemon.height.m) + " m",
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    border = borderStroke,
                                    shape = MaterialTheme.shapes.small
                                )
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = "Height",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Text(
                            text = "%.2f".format(pokemon.weight.kg) + " kg",
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    border = borderStroke,
                                    shape = MaterialTheme.shapes.small
                                )
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Weight",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
        spacer(dp = 16.dp)
        Text(
            text = "Abilities",
            modifier = Modifier.align(CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )
        spacer(dp = 8.dp)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (pokemon.normalAbilities.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        pokemon.normalAbilities.forEach {
                            Text(
                                text = it.name,
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        color = pokemon.types.first().typeContainerColor,
                                        shape = MaterialTheme.shapes.small
                                    )
                                    .padding(vertical = 8.dp, horizontal = 16.dp),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium,
                                color = pokemon.types.first().onTypeContainerColor,
                            )
                        }
                    }
                }
                if (pokemon.hiddenAbility != EmptyAbility) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = pokemon.types.first().typeContainerColor,
                                shape = MaterialTheme.shapes.small
                            ),
                    ) {
                        Text(
                            text = "Hidden",
                            modifier = Modifier
                                .background(
                                    color = pokemon.types.first().typeContainerColor,
                                    shape = MaterialTheme.shapes.small.copy(
                                        topEnd = CornerSize(0),
                                        bottomEnd = CornerSize(0)
                                    )
                                )
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = pokemon.hiddenAbility.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = pokemon.types.first().typeContainerColor
                                        .copy(alpha = 0.4f),
                                    shape = MaterialTheme.shapes.small.copy(
                                        topStart = CornerSize(0),
                                        bottomStart = CornerSize(0)
                                    )
                                )
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        spacer(dp = 16.dp)
        Text(
            text = "Base Stats",
            modifier = Modifier.align(CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun PokemonMoves(
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
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
        horizontalAlignment = CenterHorizontally
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
        horizontalAlignment = CenterHorizontally
    ) {
        Text(text = "Menu")
    }
}

@Composable
fun TabRowMenu(
    selectedIndex: Int,
    onTabChanged: (Int) -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor
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
                    horizontalAlignment = CenterHorizontally
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = "Icon of tab ${tab.displayName}"
                    )
                    if (selected) {
                        Text(
                            text = tab.displayName,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

private enum class DetailsTab(
    val icon: ImageVector,
    val displayName: String
) {
    INFO(
        icon = Filled.Info,
        displayName = "Info"
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

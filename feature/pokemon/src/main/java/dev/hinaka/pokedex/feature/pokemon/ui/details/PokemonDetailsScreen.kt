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
package dev.hinaka.pokedex.feature.pokemon.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.hinaka.pokedex.core.designsystem.component.PokedexIcon
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.icon.Icon
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.core.designsystem.theme.overlaySurface
import dev.hinaka.pokedex.core.ui.pokemon.PokemonId
import dev.hinaka.pokedex.core.ui.pokemon.PokemonName
import dev.hinaka.pokedex.core.ui.type.PokemonTypes
import dev.hinaka.pokedex.core.ui.type.getTypeContainerColors
import dev.hinaka.pokedex.core.ui.type.onTypeContainerColor
import dev.hinaka.pokedex.core.ui.type.typeContainerColor
import dev.hinaka.pokedex.core.ui.utils.preview.PokedexPreviews
import dev.hinaka.pokedex.core.ui.utils.preview.PokemonPreviewParameterProvider
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.feature.pokemon.R
import dev.hinaka.pokedex.feature.pokemon.R.string
import dev.hinaka.pokedex.feature.pokemon.ui.details.PokemonDetailsTab.INFO
import dev.hinaka.pokedex.feature.pokemon.ui.details.PokemonDetailsTab.MENU
import dev.hinaka.pokedex.feature.pokemon.ui.details.PokemonDetailsTab.MORE
import dev.hinaka.pokedex.feature.pokemon.ui.details.PokemonDetailsTab.MOVES

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PokemonDetailsScreen(
    pokemon: Pokemon,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (containerColor, contentColor) = pokemon.types.getTypeContainerColors()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Filled.ArrowBack,
                            contentDescription = "Back Icon"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = containerColor,
                    navigationIconContentColor = contentColor
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        PokemonDetails(
            pokemon = pokemon,
            modifier = Modifier.consumedWindowInsets(innerPadding),
            contentPadding = innerPadding
        )
    }
}

@Composable
fun PokemonDetails(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    var selectedIndex by remember { mutableStateOf(0) }

    val (containerColor, contentColor) = pokemon.types.getTypeContainerColors()

    Surface(
        modifier = modifier.padding(contentPadding),
        color = containerColor,
        contentColor = contentColor
    ) {
        Column {
            PokemonHeader(
                id = pokemon.id,
                name = pokemon.name,
                types = pokemon.types,
                imageUrl = pokemon.imageUrl,
                containerColor = containerColor,
                contentColor = contentColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            TabContent(
                tab = PokemonDetailsTab.values()[selectedIndex],
                pokemon = pokemon,
                containerColor = containerColor,
                contentColor = contentColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            )
            PokemonTabRow(
                selectedIndex = selectedIndex,
                onTabChanged = { selectedIndex = it },
                modifier = Modifier.fillMaxWidth(),
                containerColor = containerColor,
                contentColor = contentColor
            )
        }
    }
}

@Composable
private fun PokemonHeader(
    id: Id,
    name: String,
    types: List<Type>,
    imageUrl: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Row(
            modifier = Modifier
                .height(Min)
                .background(colorScheme.overlaySurface)
                .padding(start = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Center
            ) {
                Row(
                    verticalAlignment = CenterVertically
                ) {
                    PokemonName(
                        name = name,
                        modifier = Modifier.weight(1f),
                        style = typography.headlineMedium
                    )
                    Space(dp = 8.dp)
                    PokemonId(
                        id = id,
                        style = typography.headlineSmall
                    )
                }
                PokemonTypes(
                    types = types,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
            Space(dp = 8.dp)
            PokemonImage(
                imageUrl = imageUrl,
                imageDescription = stringResource(
                    id = string.pokemon_image_description,
                    name
                ),
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
        color = colorScheme.overlaySurface,
        shape = RoundedCornerShape(
            topStartPercent = 50,
            bottomStartPercent = 50
        )
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = imageDescription,
            placeholder = painterResource(PokedexIcons.PokeBall.id),
            modifier = Modifier
                .defaultMinSize(minHeight = 120.dp)
                .padding(start = 16.dp)
        )
    }
}

@Composable
private fun TabContent(
    tab: PokemonDetailsTab,
    pokemon: Pokemon,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    when (tab) {
        INFO -> InfoSections(
            pokemon = pokemon,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = modifier
        )
        MOVES -> MovesSections(
            pokemon = pokemon,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = modifier
        )
        MORE -> ExtraInfoSections(
            modifier = modifier
        )
        MENU -> MenuSections(
            modifier = modifier
        )
    }
}

@Composable
private fun PokemonTabRow(
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
        contentColor = contentColor,
        indicator = {},
        divider = {}
    ) {
        PokemonDetailsTab.values().mapIndexed { index, tab ->
            PokemonTab(
                selected = index == selectedIndex,
                onClick = { onTabChanged(index) },
                label = tab.label,
                icon = tab.icon
            )
        }
    }
}

@Composable
private fun PokemonTab(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    icon: Icon
) {
    val selectedColor = LocalContentColor.current
    val unselectedColor = selectedColor.copy(alpha = 0.4f)
    Tab(
        selected = selected,
        onClick = onClick,
        selectedContentColor = selectedColor,
        unselectedContentColor = unselectedColor
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .height(48.dp)
                .fillMaxWidth(),
            verticalArrangement = Center,
            horizontalAlignment = CenterHorizontally
        ) {
            PokedexIcon(
                icon = icon,
                contentDescription = stringResource(
                    id = R.string.pokemon_details_tab_icon_description,
                    label
                )
            )
            if (selected) {
                Text(
                    text = label,
                    style = typography.bodyLarge
                )
            }
        }
    }
}

private enum class PokemonDetailsTab(
    val icon: Icon,
    val labelId: Int
) {
    INFO(
        icon = PokedexIcons.Info,
        labelId = R.string.pokemon_details_tab_info
    ),
    MOVES(
        icon = PokedexIcons.Move,
        labelId = R.string.pokemon_details_tab_moves
    ),
    MORE(
        icon = PokedexIcons.Plus,
        labelId = R.string.pokemon_details_tab_more
    ),
    MENU(
        icon = PokedexIcons.Menu,
        labelId = R.string.pokemon_details_tab_menu
    );

    val label @Composable get() = stringResource(id = labelId)
}

@PokedexPreviews
@Composable
private fun PokemonHeaderPreviews(
    @PreviewParameter(PokemonPreviewParameterProvider::class, limit = 1) pokemon: Pokemon
) {
    PokedexTheme {
        val (containerColor, contentColor) = pokemon.types.getTypeContainerColors()

        PokemonHeader(
            id = pokemon.id,
            name = pokemon.name,
            types = pokemon.types,
            imageUrl = pokemon.imageUrl,
            containerColor = containerColor,
            contentColor = contentColor
        )
    }
}

@PokedexPreviews
@Composable
private fun PokemonTabRowPreviews(
    @PreviewParameter(PokemonPreviewParameterProvider::class, limit = 1) pokemon: Pokemon
) {
    PokedexTheme {
        val (containerColor, contentColor) = pokemon.types.getTypeContainerColors()

        Column(verticalArrangement = spacedBy(8.dp)) {
            PokemonDetailsTab.values().forEachIndexed { index, _ ->
                PokemonTabRow(
                    selectedIndex = index,
                    onTabChanged = {},
                    containerColor = containerColor,
                    contentColor = contentColor
                )
            }
        }
    }
}

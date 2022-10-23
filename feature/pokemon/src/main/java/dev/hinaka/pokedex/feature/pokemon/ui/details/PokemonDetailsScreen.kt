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

import android.util.Log
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.PokedexIcon
import dev.hinaka.pokedex.core.designsystem.icon.Icon
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.core.ui.pokemon.PokemonInfoCard
import dev.hinaka.pokedex.core.ui.type.getTypeContainerColors
import dev.hinaka.pokedex.core.ui.type.typeColor
import dev.hinaka.pokedex.core.ui.utils.preview.PokedexPreviews
import dev.hinaka.pokedex.core.ui.utils.preview.PokemonPreviewParameterProvider
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.feature.pokemon.R
import dev.hinaka.pokedex.feature.pokemon.ui.details.PokemonDetailsTab.INFO
import dev.hinaka.pokedex.feature.pokemon.ui.details.PokemonDetailsTab.MENU
import dev.hinaka.pokedex.feature.pokemon.ui.details.PokemonDetailsTab.MORE
import dev.hinaka.pokedex.feature.pokemon.ui.details.PokemonDetailsTab.MOVES

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PokemonDetailsScreen(
    pokemon: Pokemon,
    previousPokemon: Pokemon?,
    nextPokemon: Pokemon?,
    onBackClick: () -> Unit,
    onSelectPokemon: (Pokemon) -> Unit,
    onSelectHome: () -> Unit,
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
            previousPokemon = previousPokemon,
            nextPokemon = nextPokemon,
            onSelectPokemon = onSelectPokemon,
            onSelectHome = onSelectHome,
            modifier = Modifier.consumedWindowInsets(innerPadding),
            contentPadding = innerPadding
        )
    }
}

@Composable
fun PokemonDetails(
    pokemon: Pokemon,
    previousPokemon: Pokemon?,
    nextPokemon: Pokemon?,
    onSelectPokemon: (Pokemon) -> Unit,
    onSelectHome: () -> Unit,
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
            PokemonInfoCard(
                id = pokemon.id,
                name = pokemon.name,
                genus = pokemon.genus,
                types = pokemon.types,
                imageUrl = pokemon.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            TabContent(
                tab = PokemonDetailsTab.values()[selectedIndex],
                pokemon = pokemon,
                previousPokemon = previousPokemon,
                nextPokemon = nextPokemon,
                onSelectPokemon = onSelectPokemon,
                onSelectHome = onSelectHome,
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
private fun TabContent(
    tab: PokemonDetailsTab,
    pokemon: Pokemon,
    previousPokemon: Pokemon?,
    nextPokemon: Pokemon?,
    onSelectPokemon: (Pokemon) -> Unit,
    onSelectHome: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    val typeColor = pokemon.types.first().typeColor

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
            pokemon = pokemon,
            modifier = modifier
        )
        MENU -> MenuSections(
            previousPokemon = previousPokemon,
            nextPokemon = nextPokemon,
            typeColor = typeColor,
            onSelectPokemon = onSelectPokemon,
            onSelectHome = onSelectHome,
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
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

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import dev.hinaka.pokedex.core.ui.type.onTypeContainerColor
import dev.hinaka.pokedex.core.ui.type.typeContainerColor
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.feature.pokemon.PokemonScreenUiState

@Composable
fun PokemonScreen(
    uiState: PokemonScreenUiState,
    openDrawer: () -> Unit,
    onSelectPokemon: (Pokemon) -> Unit,
    onUnselectPokemon: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyPagingItems = uiState.pokemonPagingFlow.collectAsLazyPagingItems()
    val lazyListState = rememberLazyListState()

    when (val selectedPokemon = uiState.selectedPokemon) {
        null -> PokemonListScreen(
            lazyPagingItems = lazyPagingItems,
            onSelectPokemon = onSelectPokemon,
            openDrawer = openDrawer,
            modifier = modifier,
            state = lazyListState
        )
        else -> {
            PokemonDetailScreen(
                pokemon = selectedPokemon,
                onBackClick = onUnselectPokemon,
                modifier = modifier
            )
            BackHandler(onBack = onUnselectPokemon)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PokemonListScreen(
    lazyPagingItems: LazyPagingItems<Pokemon>,
    onSelectPokemon: (Pokemon) -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Pokédex") },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Drawer Icon"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        PokemonList(
            lazyPagingItems = lazyPagingItems,
            onSelectPokemon = onSelectPokemon,
            modifier = Modifier.consumedWindowInsets(innerPadding),
            state = state,
            contentPadding = innerPadding
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PokemonDetailScreen(
    pokemon: Pokemon,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = pokemon.types.first().typeContainerColor
    val contentColor = pokemon.types.first().onTypeContainerColor

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
        },
    ) { innerPadding ->
        PokemonDetails(
            pokemon = pokemon,
            modifier = Modifier.consumedWindowInsets(innerPadding),
            contentPadding = innerPadding
        )
    }
}

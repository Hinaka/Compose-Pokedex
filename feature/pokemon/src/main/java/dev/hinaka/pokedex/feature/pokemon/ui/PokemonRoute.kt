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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.feature.pokemon.ui.PokemonScreenType.Details
import dev.hinaka.pokedex.feature.pokemon.ui.PokemonScreenType.List
import dev.hinaka.pokedex.feature.pokemon.ui.details.PokemonDetailsScreen
import dev.hinaka.pokedex.feature.pokemon.ui.list.PokemonListScreen

@Composable
fun PokemonRoute(
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    pokemonViewModel: PokemonViewModel = hiltViewModel()
) {
    val uiState by pokemonViewModel.uiState.collectAsState()

    PokemonRoute(
        uiState = uiState,
        openDrawer = openDrawer,
        onSelectPokemon = pokemonViewModel::selectPokemon,
        onUnselectPokemon = pokemonViewModel::unselectPokemon,
        onSelectHome = pokemonViewModel::clearSelectedPokemons,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonRoute(
    uiState: PokemonUiState,
    openDrawer: () -> Unit,
    onSelectPokemon: (Pokemon) -> Unit,
    onUnselectPokemon: () -> Unit,
    onSelectHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyPagingItems = uiState.pokemonPagingFlow.collectAsLazyPagingItems()
    val lazyListState = rememberLazyListState()
    val pokemonListTopAppBarState = rememberTopAppBarState()

    when (getScreenType(uiState)) {
        List -> {
            PokemonListScreen(
                lazyPagingItems = lazyPagingItems,
                onSelectPokemon = onSelectPokemon,
                openDrawer = openDrawer,
                modifier = modifier,
                state = lazyListState,
                topAppBarState = pokemonListTopAppBarState
            )
        }

        Details -> {
            check(uiState.selectedPokemon != null)

            PokemonDetailsScreen(
                pokemon = uiState.selectedPokemon,
                previousPokemon = uiState.previousPokemon,
                nextPokemon = uiState.nextPokemon,
                onBackClick = onUnselectPokemon,
                onSelectPokemon = onSelectPokemon,
                onSelectHome = onSelectHome,
                modifier = modifier
            )

            BackHandler(onBack = onUnselectPokemon)
        }
    }
}

private enum class PokemonScreenType {
    List,
    Details
}

private fun getScreenType(
    uiState: PokemonUiState
): PokemonScreenType = when (uiState.selectedPokemon) {
    null -> List
    else -> Details
}
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
package dev.hinaka.pokedex.feature.pokemon

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.hinaka.pokedex.domain.Pokemon

@Composable
fun PokemonRoute(
    modifier: Modifier = Modifier,
    pokemonViewModel: PokemonViewModel = viewModel()
) {
    val uiState by pokemonViewModel.uiState.collectAsState()
    val paging = pokemonViewModel.pokemonPaging.collectAsLazyPagingItems()

    PokemonScreen(
        pokemons = uiState.pokemons,
        paging = paging,
        modifier = modifier
    )
}

@Composable
fun PokemonScreen(
    pokemons: List<Pokemon>,
    paging: LazyPagingItems<Pokemon>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(paging) { pokemon ->
            Text(text = pokemon?.name.orEmpty())
        }
    }
}

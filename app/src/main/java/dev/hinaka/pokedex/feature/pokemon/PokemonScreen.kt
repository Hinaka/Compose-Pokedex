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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import dev.hinaka.pokedex.R
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.domain.Stats
import dev.hinaka.pokedex.domain.Type
import dev.hinaka.pokedex.domain.Type.BUG
import dev.hinaka.pokedex.domain.Type.DARK
import dev.hinaka.pokedex.domain.Type.DRAGON
import dev.hinaka.pokedex.domain.Type.ELECTRIC
import dev.hinaka.pokedex.domain.Type.FAIRY
import dev.hinaka.pokedex.domain.Type.FIGHTING
import dev.hinaka.pokedex.domain.Type.FIRE
import dev.hinaka.pokedex.domain.Type.FLYING
import dev.hinaka.pokedex.domain.Type.GHOST
import dev.hinaka.pokedex.domain.Type.GRASS
import dev.hinaka.pokedex.domain.Type.GROUND
import dev.hinaka.pokedex.domain.Type.ICE
import dev.hinaka.pokedex.domain.Type.NORMAL
import dev.hinaka.pokedex.domain.Type.POISON
import dev.hinaka.pokedex.domain.Type.PSYCHIC
import dev.hinaka.pokedex.domain.Type.ROCK
import dev.hinaka.pokedex.domain.Type.STEEL
import dev.hinaka.pokedex.domain.Type.UNKNOWN
import dev.hinaka.pokedex.domain.Type.WATER
import kotlinx.coroutines.flow.Flow

@Composable
fun PokemonRoute(
    modifier: Modifier = Modifier,
    pokemonViewModel: PokemonViewModel = viewModel()
) {
    val uiState by pokemonViewModel.uiState.collectAsState()

    PokemonScreen(
        pokemonPagingFlow = uiState.pokemonPagingFlow,
        modifier = modifier
    )
}

@Composable
fun PokemonScreen(
    pokemonPagingFlow: Flow<PagingData<Pokemon>>,
    modifier: Modifier = Modifier
) {
    val lazyPagingItems = pokemonPagingFlow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(lazyPagingItems, { it.id.value }) { pokemon ->
            pokemon?.let {
                PokemonItem(pokemon = it, modifier = Modifier.padding(24.dp))
            }
        }
    }
}

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "#${pokemon.id.toString().padStart(3, '0')}")
                    Text(text = pokemon.name)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    pokemon.types.forEach {
                        Text(text = it.displayName)
                    }
                }
            }
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = "Image of ${pokemon.name}",
                placeholder = painterResource(id = R.drawable.ic_pokeball),
                modifier = Modifier.size(64.dp)
            )
        }
    }
}

@Preview
@Composable
fun PokemonItemPreview() {
    PokedexTheme {
        PokemonItem(
            pokemon = Pokemon(
                id = Id(1),
                name = "bulbasaur",
                types = listOf(Type.GRASS, Type.BUG),
                imageUrl = "",
                abilities = emptyList(),
                baseMoves = emptyList(),
                baseStats = Stats(0, 0, 0, 0, 0, 0)
            )
        )
    }
}

private val Type.displayName: String
    @Composable get() = when (this) {
        NORMAL -> stringResource(id = R.string.type_normal)
        FIGHTING -> stringResource(id = R.string.type_fighting)
        FLYING -> stringResource(id = R.string.type_flying)
        POISON -> stringResource(id = R.string.type_poison)
        GROUND -> stringResource(id = R.string.type_ground)
        ROCK -> stringResource(id = R.string.type_rock)
        BUG -> stringResource(id = R.string.type_bug)
        GHOST -> stringResource(id = R.string.type_ghost)
        STEEL -> stringResource(id = R.string.type_steel)
        FIRE -> stringResource(id = R.string.type_fire)
        WATER -> stringResource(id = R.string.type_water)
        GRASS -> stringResource(id = R.string.type_grass)
        ELECTRIC -> stringResource(id = R.string.type_electric)
        PSYCHIC -> stringResource(id = R.string.type_psychic)
        ICE -> stringResource(id = R.string.type_ice)
        DRAGON -> stringResource(id = R.string.type_dragon)
        DARK -> stringResource(id = R.string.type_dark)
        FAIRY -> stringResource(id = R.string.type_fairy)
        UNKNOWN -> stringResource(id = R.string.type_unknown)
    }

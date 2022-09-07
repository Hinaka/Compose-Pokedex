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
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(lazyPagingItems, { it.id.value }) { pokemon ->
            pokemon?.let {
                PokemonItem(pokemon = it, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(Min)
                .padding(start = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PokemonId(id = pokemon.id)
                    Spacer(modifier = Modifier.width(4.dp))
                    PokemonName(name = pokemon.name)
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
fun PokemonId(
    id: Id,
    modifier: Modifier = Modifier,
) {
    val idText = id.toString().padStart(3, '0')
    Text(
        text = stringResource(id = R.string.pokemon_id_format, idText),
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun PokemonName(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = name.replaceFirstChar { it.uppercase() },
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun PokemonImage(
    imageUrl: String,
    imageDescription: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = Color.White.copy(alpha = 0.4f),
        shape = RoundedCornerShape(
            topStartPercent = 50,
            bottomStartPercent = 50
        )
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = imageDescription,
            placeholder = painterResource(id = R.drawable.ic_pokeball),
            modifier = Modifier
                .defaultMinSize(minHeight = 80.dp)
                .padding(start = 16.dp)
        )
    }
}

@Composable
fun PokemonTypes(
    types: List<Type>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        types.forEach { type ->
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 2.dp),
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = CircleShape,
            ) {
                Text(
                    text = type.displayName.uppercase(),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                )
            }
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

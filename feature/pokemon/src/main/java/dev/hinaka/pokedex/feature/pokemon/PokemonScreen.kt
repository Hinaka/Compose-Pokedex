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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
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
    pokemonViewModel: PokemonViewModel = hiltViewModel()
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
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = pokemon.types.first().typeContainerColor,
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
    modifier: Modifier = Modifier
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
    modifier: Modifier = Modifier
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
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        types.forEach { type ->
            Card(
                modifier = Modifier.weight(1f),
                shape = CircleShape,
                border = BorderStroke(1.dp, type.typeColor),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = Color.Transparent,
                    contentColor = type.typeColor
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = type.iconPainter,
                        contentDescription = "icon of type ${type.displayName}",
                        modifier = Modifier.size(16.dp),
                        colorFilter = ColorFilter.tint(type.typeColor)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = type.displayName.uppercase(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
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

private val Type.iconPainter: Painter
    @Composable get() = when (this) {
        NORMAL -> painterResource(id = PokedexIcons.TypeNormal)
        FIGHTING -> painterResource(id = PokedexIcons.TypeFighting)
        FLYING -> painterResource(id = PokedexIcons.TypeFlying)
        POISON -> painterResource(id = PokedexIcons.TypePoison)
        GROUND -> painterResource(id = PokedexIcons.TypeGround)
        ROCK -> painterResource(id = PokedexIcons.TypeRock)
        BUG -> painterResource(id = PokedexIcons.TypeBug)
        GHOST -> painterResource(id = PokedexIcons.TypeGhost)
        STEEL -> painterResource(id = PokedexIcons.TypeSteel)
        FIRE -> painterResource(id = PokedexIcons.TypeFire)
        WATER -> painterResource(id = PokedexIcons.TypeWater)
        GRASS -> painterResource(id = PokedexIcons.TypeGrass)
        ELECTRIC -> painterResource(id = PokedexIcons.TypeElectric)
        PSYCHIC -> painterResource(id = PokedexIcons.TypePsychic)
        ICE -> painterResource(id = PokedexIcons.TypeIce)
        DRAGON -> painterResource(id = PokedexIcons.TypeDragon)
        DARK -> painterResource(id = PokedexIcons.TypeDark)
        FAIRY -> painterResource(id = PokedexIcons.TypeFairy)
        UNKNOWN -> painterResource(id = R.drawable.ic_pokeball)
    }

private val Type.typeColor
    @Composable get() = when (this) {
        NORMAL -> PokedexTheme.colors.typeNormal
        FIGHTING -> PokedexTheme.colors.typeFighting
        FLYING -> PokedexTheme.colors.typeFlying
        POISON -> PokedexTheme.colors.typePoison
        GROUND -> PokedexTheme.colors.typeGround
        ROCK -> PokedexTheme.colors.typeRock
        BUG -> PokedexTheme.colors.typeBug
        GHOST -> PokedexTheme.colors.typeGhost
        STEEL -> PokedexTheme.colors.typeSteel
        FIRE -> PokedexTheme.colors.typeFire
        WATER -> PokedexTheme.colors.typeWater
        GRASS -> PokedexTheme.colors.typeGrass
        ELECTRIC -> PokedexTheme.colors.typeElectric
        PSYCHIC -> PokedexTheme.colors.typePsychic
        ICE -> PokedexTheme.colors.typeIce
        DRAGON -> PokedexTheme.colors.typeDragon
        DARK -> PokedexTheme.colors.typeDark
        FAIRY -> PokedexTheme.colors.typeFairy
        UNKNOWN -> PokedexTheme.colors.typeUnknown
    }

private val Type.onTypeColor
    @Composable get() = when (this) {
        NORMAL -> PokedexTheme.colors.onTypeNormal
        FIGHTING -> PokedexTheme.colors.onTypeFighting
        FLYING -> PokedexTheme.colors.onTypeFlying
        POISON -> PokedexTheme.colors.onTypePoison
        GROUND -> PokedexTheme.colors.onTypeGround
        ROCK -> PokedexTheme.colors.onTypeRock
        BUG -> PokedexTheme.colors.onTypeBug
        GHOST -> PokedexTheme.colors.onTypeGhost
        STEEL -> PokedexTheme.colors.onTypeSteel
        FIRE -> PokedexTheme.colors.onTypeFire
        WATER -> PokedexTheme.colors.onTypeWater
        GRASS -> PokedexTheme.colors.onTypeGrass
        ELECTRIC -> PokedexTheme.colors.onTypeElectric
        PSYCHIC -> PokedexTheme.colors.onTypePsychic
        ICE -> PokedexTheme.colors.onTypeIce
        DRAGON -> PokedexTheme.colors.onTypeDragon
        DARK -> PokedexTheme.colors.onTypeDark
        FAIRY -> PokedexTheme.colors.onTypeFairy
        UNKNOWN -> PokedexTheme.colors.onTypeUnknown
    }

private val Type.typeContainerColor
    @Composable get() = when (this) {
        NORMAL -> PokedexTheme.colors.typeNormalContainer
        FIGHTING -> PokedexTheme.colors.typeFightingContainer
        FLYING -> PokedexTheme.colors.typeFlyingContainer
        POISON -> PokedexTheme.colors.typePoisonContainer
        GROUND -> PokedexTheme.colors.typeGroundContainer
        ROCK -> PokedexTheme.colors.typeRockContainer
        BUG -> PokedexTheme.colors.typeBugContainer
        GHOST -> PokedexTheme.colors.typeGhostContainer
        STEEL -> PokedexTheme.colors.typeSteelContainer
        FIRE -> PokedexTheme.colors.typeFireContainer
        WATER -> PokedexTheme.colors.typeWaterContainer
        GRASS -> PokedexTheme.colors.typeGrassContainer
        ELECTRIC -> PokedexTheme.colors.typeElectricContainer
        PSYCHIC -> PokedexTheme.colors.typePsychicContainer
        ICE -> PokedexTheme.colors.typeIceContainer
        DRAGON -> PokedexTheme.colors.typeDragonContainer
        DARK -> PokedexTheme.colors.typeDarkContainer
        FAIRY -> PokedexTheme.colors.typeFairyContainer
        UNKNOWN -> PokedexTheme.colors.typeUnknownContainer
    }

private val Type.onTypeContainerColor
    @Composable get() = when (this) {
        NORMAL -> PokedexTheme.colors.onTypeNormalContainer
        FIGHTING -> PokedexTheme.colors.onTypeFightingContainer
        FLYING -> PokedexTheme.colors.onTypeFlyingContainer
        POISON -> PokedexTheme.colors.onTypePoisonContainer
        GROUND -> PokedexTheme.colors.onTypeGroundContainer
        ROCK -> PokedexTheme.colors.onTypeRockContainer
        BUG -> PokedexTheme.colors.onTypeBugContainer
        GHOST -> PokedexTheme.colors.onTypeGhostContainer
        STEEL -> PokedexTheme.colors.onTypeSteelContainer
        FIRE -> PokedexTheme.colors.onTypeFireContainer
        WATER -> PokedexTheme.colors.onTypeWaterContainer
        GRASS -> PokedexTheme.colors.onTypeGrassContainer
        ELECTRIC -> PokedexTheme.colors.onTypeElectricContainer
        PSYCHIC -> PokedexTheme.colors.onTypePsychicContainer
        ICE -> PokedexTheme.colors.onTypeIceContainer
        DRAGON -> PokedexTheme.colors.onTypeDragonContainer
        DARK -> PokedexTheme.colors.onTypeDarkContainer
        FAIRY -> PokedexTheme.colors.onTypeFairyContainer
        UNKNOWN -> PokedexTheme.colors.onTypeUnknownContainer
    }

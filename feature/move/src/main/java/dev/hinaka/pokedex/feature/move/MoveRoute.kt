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
package dev.hinaka.pokedex.feature.move

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.hinaka.pokedex.core.designsystem.component.PokedexImage
import dev.hinaka.pokedex.core.designsystem.icon.Icon
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.domain.DamageClass
import dev.hinaka.pokedex.domain.DamageClass.PHYSICAL
import dev.hinaka.pokedex.domain.DamageClass.SPECIAL
import dev.hinaka.pokedex.domain.DamageClass.STATUS
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Move
import dev.hinaka.pokedex.domain.type.TypeIdentifier
import dev.hinaka.pokedex.domain.type.TypeIdentifier.BUG
import dev.hinaka.pokedex.domain.type.TypeIdentifier.DARK
import dev.hinaka.pokedex.domain.type.TypeIdentifier.DRAGON
import dev.hinaka.pokedex.domain.type.TypeIdentifier.ELECTRIC
import dev.hinaka.pokedex.domain.type.TypeIdentifier.FAIRY
import dev.hinaka.pokedex.domain.type.TypeIdentifier.FIGHTING
import dev.hinaka.pokedex.domain.type.TypeIdentifier.FIRE
import dev.hinaka.pokedex.domain.type.TypeIdentifier.FLYING
import dev.hinaka.pokedex.domain.type.TypeIdentifier.GHOST
import dev.hinaka.pokedex.domain.type.TypeIdentifier.GRASS
import dev.hinaka.pokedex.domain.type.TypeIdentifier.GROUND
import dev.hinaka.pokedex.domain.type.TypeIdentifier.ICE
import dev.hinaka.pokedex.domain.type.TypeIdentifier.NORMAL
import dev.hinaka.pokedex.domain.type.TypeIdentifier.POISON
import dev.hinaka.pokedex.domain.type.TypeIdentifier.PSYCHIC
import dev.hinaka.pokedex.domain.type.TypeIdentifier.ROCK
import dev.hinaka.pokedex.domain.type.TypeIdentifier.STEEL
import dev.hinaka.pokedex.domain.type.TypeIdentifier.UNKNOWN
import dev.hinaka.pokedex.domain.type.TypeIdentifier.WATER
import dev.hinaka.pokedex.feature.move.R.string
import kotlinx.coroutines.flow.Flow

@Composable
fun MoveRoute(
    modifier: Modifier = Modifier,
    moveViewModel: MoveViewModel = hiltViewModel()
) {
    val uiState by moveViewModel.uiState.collectAsState()

    MoveScreen(
        movePagingFlow = uiState.movePagingFlow
    )
}

@Composable
fun MoveScreen(
    movePagingFlow: Flow<PagingData<Move>>,
    modifier: Modifier = Modifier
) {
    val lazyPagingItems = movePagingFlow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(lazyPagingItems, { it.id.value }) { item ->
            item?.let {
                Move(move = it, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun Move(
    move: Move,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseSurface
        )
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = move.name,
                modifier = Modifier.weight(4f),
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = move.power.toString(),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = move.acc.toString(),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = move.pp.toString(),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.labelMedium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(Min)
        ) {
            PokemonType(
                typeIdentifier = move.typeIdentifier,
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(8.dp))
            DamageClass(
                damageClass = move.damageClass,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun PokemonType(
    typeIdentifier: TypeIdentifier,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = typeIdentifier.typeIdentifierColor,
            contentColor = typeIdentifier.onTypeIdentifierColor
        )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            PokedexImage(
                icon = typeIdentifier.icon,
                contentDescription = "icon of type ${typeIdentifier.displayName}",
                modifier = Modifier.size(16.dp),
                colorFilter = ColorFilter.tint(typeIdentifier.onTypeIdentifierColor)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = typeIdentifier.displayName.uppercase(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun DamageClass(
    damageClass: DamageClass,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = CircleShape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = damageClass.displayName,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovePreview() {
    PokedexTheme {
        Move(
            move = Move(
                id = Id(1),
                name = "Pound",
                typeIdentifier = NORMAL,
                damageClass = PHYSICAL,
                power = 40,
                acc = 100,
                pp = 35
            )
        )
    }
}

private val TypeIdentifier.displayName: String
    @Composable get() = when (this) {
        NORMAL -> stringResource(id = string.type_normal)
        FIGHTING -> stringResource(id = string.type_fighting)
        FLYING -> stringResource(id = string.type_flying)
        POISON -> stringResource(id = string.type_poison)
        GROUND -> stringResource(id = string.type_ground)
        ROCK -> stringResource(id = string.type_rock)
        BUG -> stringResource(id = string.type_bug)
        GHOST -> stringResource(id = string.type_ghost)
        STEEL -> stringResource(id = string.type_steel)
        FIRE -> stringResource(id = string.type_fire)
        WATER -> stringResource(id = string.type_water)
        GRASS -> stringResource(id = string.type_grass)
        ELECTRIC -> stringResource(id = string.type_electric)
        PSYCHIC -> stringResource(id = string.type_psychic)
        ICE -> stringResource(id = string.type_ice)
        DRAGON -> stringResource(id = string.type_dragon)
        DARK -> stringResource(id = string.type_dark)
        FAIRY -> stringResource(id = string.type_fairy)
        UNKNOWN -> stringResource(id = string.type_unknown)
    }

private val TypeIdentifier.icon: Icon
    @Composable get() = when (this) {
        NORMAL -> PokedexIcons.TypeNormal
        FIGHTING -> PokedexIcons.TypeFighting
        FLYING -> PokedexIcons.TypeFlying
        POISON -> PokedexIcons.TypePoison
        GROUND -> PokedexIcons.TypeGround
        ROCK -> PokedexIcons.TypeRock
        BUG -> PokedexIcons.TypeBug
        GHOST -> PokedexIcons.TypeGhost
        STEEL -> PokedexIcons.TypeSteel
        FIRE -> PokedexIcons.TypeFire
        WATER -> PokedexIcons.TypeWater
        GRASS -> PokedexIcons.TypeGrass
        ELECTRIC -> PokedexIcons.TypeElectric
        PSYCHIC -> PokedexIcons.TypePsychic
        ICE -> PokedexIcons.TypeIce
        DRAGON -> PokedexIcons.TypeDragon
        DARK -> PokedexIcons.TypeDark
        FAIRY -> PokedexIcons.TypeFairy
        UNKNOWN -> PokedexIcons.PokeBall
    }

private val TypeIdentifier.typeIdentifierColor
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

private val TypeIdentifier.onTypeIdentifierColor
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

private val DamageClass.displayName
    @Composable get() = when (this) {
        STATUS -> "STATUS"
        PHYSICAL -> "PHYSICAL"
        SPECIAL -> "SPECIAL"
    }

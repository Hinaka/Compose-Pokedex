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
package dev.hinaka.pokedex.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.domain.type.Type.Identifier
import dev.hinaka.pokedex.domain.type.Type.Identifier.BUG
import dev.hinaka.pokedex.domain.type.Type.Identifier.DARK
import dev.hinaka.pokedex.domain.type.Type.Identifier.DRAGON
import dev.hinaka.pokedex.domain.type.Type.Identifier.ELECTRIC
import dev.hinaka.pokedex.domain.type.Type.Identifier.FAIRY
import dev.hinaka.pokedex.domain.type.Type.Identifier.FIGHTING
import dev.hinaka.pokedex.domain.type.Type.Identifier.FIRE
import dev.hinaka.pokedex.domain.type.Type.Identifier.FLYING
import dev.hinaka.pokedex.domain.type.Type.Identifier.GHOST
import dev.hinaka.pokedex.domain.type.Type.Identifier.GRASS
import dev.hinaka.pokedex.domain.type.Type.Identifier.GROUND
import dev.hinaka.pokedex.domain.type.Type.Identifier.ICE
import dev.hinaka.pokedex.domain.type.Type.Identifier.NORMAL
import dev.hinaka.pokedex.domain.type.Type.Identifier.POISON
import dev.hinaka.pokedex.domain.type.Type.Identifier.PSYCHIC
import dev.hinaka.pokedex.domain.type.Type.Identifier.ROCK
import dev.hinaka.pokedex.domain.type.Type.Identifier.STEEL
import dev.hinaka.pokedex.domain.type.Type.Identifier.WATER

@Composable
fun PokemonType(
    type: Type,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
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
                contentDescription = "icon of type ${type.name}",
                modifier = Modifier.size(16.dp),
                colorFilter = ColorFilter.tint(type.typeColor)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = type.name.uppercase(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview
@Composable
fun PokemonTypePreview() {
    PokedexTheme {
        Column(
            modifier = Modifier.width(Min),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Identifier.values().forEachIndexed { index, identifier ->
                PokemonType(
                    type = Type(
                        id = Id(index),
                        name = identifier.name,
                        identifier = identifier
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

val Type.iconPainter: Painter
    @Composable get() = when (this.identifier) {
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
    }

val Type.typeColor
    @Composable get() = when (this.identifier) {
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
    }

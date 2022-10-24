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
package dev.hinaka.pokedex.core.ui.type

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.domain.type.Type
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

val Type.typeColor
    @Composable get() = when (identifier) {
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

val Type.onTypeColor
    @Composable get() = when (identifier) {
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
    }

val Type.typeContainerColor
    @Composable get() = when (identifier) {
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
    }

val Type.onTypeContainerColor
    @Composable get() = when (identifier) {
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
    }

data class TypeColors(
    val containerColor: Color,
    val contentColor: Color
)

@Composable
fun List<Type>.getTypeContainerColors(): TypeColors {
    val primaryType = first()
    return TypeColors(
        containerColor = primaryType.typeContainerColor,
        contentColor = primaryType.onTypeContainerColor
    )
}

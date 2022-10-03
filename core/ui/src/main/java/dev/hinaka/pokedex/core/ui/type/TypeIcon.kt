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
import dev.hinaka.pokedex.core.designsystem.icon.Icon
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
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

val Type.icon: Icon
    @Composable get() = when (identifier) {
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
    }

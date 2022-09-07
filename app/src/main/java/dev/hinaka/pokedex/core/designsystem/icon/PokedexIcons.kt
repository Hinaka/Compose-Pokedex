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
package dev.hinaka.pokedex.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import dev.hinaka.pokedex.R

object PokedexIcons {
    val TypeBug = R.drawable.ic_type_bug
    val TypeDark = R.drawable.ic_type_dark
    val TypeDragon = R.drawable.ic_type_dragon
    val TypeElectric = R.drawable.ic_type_electric
    val TypeFairy = R.drawable.ic_type_fairy
    val TypeFighting = R.drawable.ic_type_fighting
    val TypeFire = R.drawable.ic_type_fire
    val TypeFlying = R.drawable.ic_type_flying
    val TypeGhost = R.drawable.ic_type_ghost
    val TypeGrass = R.drawable.ic_type_grass
    val TypeGround = R.drawable.ic_type_ground
    val TypeIce = R.drawable.ic_type_ice
    val TypeNormal = R.drawable.ic_type_normal
    val TypePoison = R.drawable.ic_type_poison
    val TypePsychic = R.drawable.ic_type_psychic
    val TypeRock = R.drawable.ic_type_rock
    val TypeSteel = R.drawable.ic_type_steel
    val TypeWater = R.drawable.ic_type_water
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}

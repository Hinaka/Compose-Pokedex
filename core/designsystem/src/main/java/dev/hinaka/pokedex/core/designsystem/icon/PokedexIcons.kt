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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import dev.hinaka.pokedex.core.designsystem.R
import dev.hinaka.pokedex.core.designsystem.icon.Icon.DrawableResourceIcon
import dev.hinaka.pokedex.core.designsystem.icon.Icon.ImageVectorIcon

object PokedexIcons {
    val TypeBug = DrawableResourceIcon(R.drawable.ic_type_bug)
    val TypeDark = DrawableResourceIcon(R.drawable.ic_type_dark)
    val TypeDragon = DrawableResourceIcon(R.drawable.ic_type_dragon)
    val TypeElectric = DrawableResourceIcon(R.drawable.ic_type_electric)
    val TypeFairy = DrawableResourceIcon(R.drawable.ic_type_fairy)
    val TypeFighting = DrawableResourceIcon(R.drawable.ic_type_fighting)
    val TypeFire = DrawableResourceIcon(R.drawable.ic_type_fire)
    val TypeFlying = DrawableResourceIcon(R.drawable.ic_type_flying)
    val TypeGhost = DrawableResourceIcon(R.drawable.ic_type_ghost)
    val TypeGrass = DrawableResourceIcon(R.drawable.ic_type_grass)
    val TypeGround = DrawableResourceIcon(R.drawable.ic_type_ground)
    val TypeIce = DrawableResourceIcon(R.drawable.ic_type_ice)
    val TypeNormal = DrawableResourceIcon(R.drawable.ic_type_normal)
    val TypePoison = DrawableResourceIcon(R.drawable.ic_type_poison)
    val TypePsychic = DrawableResourceIcon(R.drawable.ic_type_psychic)
    val TypeRock = DrawableResourceIcon(R.drawable.ic_type_rock)
    val TypeSteel = DrawableResourceIcon(R.drawable.ic_type_steel)
    val TypeWater = DrawableResourceIcon(R.drawable.ic_type_water)
    val PokeBall = DrawableResourceIcon(R.drawable.ic_pokeball)
    val Info = ImageVectorIcon(Icons.Filled.Info)
    val Move = ImageVectorIcon(Icons.Filled.Edit)
    val Plus = ImageVectorIcon(Icons.Filled.Add)
    val Menu = ImageVectorIcon(Icons.Filled.Menu)
    val ArrowForward = DrawableResourceIcon(R.drawable.ic_arrow_forward)
    val ArrowBack = DrawableResourceIcon(R.drawable.ic_arrow_back)
    val Home = ImageVectorIcon(Icons.Filled.Home)
    val Male = DrawableResourceIcon(R.drawable.ic_male)
    val Female = DrawableResourceIcon(R.drawable.ic_female)
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}

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
package dev.hinaka.pokedex.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class PokedexColors(
    val typeNormal: Color,
    val onTypeNormal: Color,
    val typeFighting: Color,
    val onTypeFighting: Color,
    val typeFlying: Color,
    val onTypeFlying: Color,
    val typePoison: Color,
    val onTypePoison: Color,
    val typeGround: Color,
    val onTypeGround: Color,
    val typeRock: Color,
    val onTypeRock: Color,
    val typeBug: Color,
    val onTypeBug: Color,
    val typeGhost: Color,
    val onTypeGhost: Color,
    val typeSteel: Color,
    val onTypeSteel: Color,
    val typeFire: Color,
    val onTypeFire: Color,
    val typeWater: Color,
    val onTypeWater: Color,
    val typeGrass: Color,
    val onTypeGrass: Color,
    val typeElectric: Color,
    val onTypeElectric: Color,
    val typePsychic: Color,
    val onTypePsychic: Color,
    val typeIce: Color,
    val onTypeIce: Color,
    val typeDragon: Color,
    val onTypeDragon: Color,
    val typeDark: Color,
    val onTypeDark: Color,
    val typeFairy: Color,
    val onTypeFairy: Color,
    val typeUnknown: Color,
    val onTypeUnknown: Color,
    val typeNormalContainer: Color,
    val onTypeNormalContainer: Color,
    val typeFightingContainer: Color,
    val onTypeFightingContainer: Color,
    val typeFlyingContainer: Color,
    val onTypeFlyingContainer: Color,
    val typePoisonContainer: Color,
    val onTypePoisonContainer: Color,
    val typeGroundContainer: Color,
    val onTypeGroundContainer: Color,
    val typeRockContainer: Color,
    val onTypeRockContainer: Color,
    val typeBugContainer: Color,
    val onTypeBugContainer: Color,
    val typeGhostContainer: Color,
    val onTypeGhostContainer: Color,
    val typeSteelContainer: Color,
    val onTypeSteelContainer: Color,
    val typeFireContainer: Color,
    val onTypeFireContainer: Color,
    val typeWaterContainer: Color,
    val onTypeWaterContainer: Color,
    val typeGrassContainer: Color,
    val onTypeGrassContainer: Color,
    val typeElectricContainer: Color,
    val onTypeElectricContainer: Color,
    val typePsychicContainer: Color,
    val onTypePsychicContainer: Color,
    val typeIceContainer: Color,
    val onTypeIceContainer: Color,
    val typeDragonContainer: Color,
    val onTypeDragonContainer: Color,
    val typeDarkContainer: Color,
    val onTypeDarkContainer: Color,
    val typeFairyContainer: Color,
    val onTypeFairyContainer: Color,
    val typeUnknownContainer: Color,
    val onTypeUnknownContainer: Color
)

val LocalPokedexColors = staticCompositionLocalOf {
    PokedexColors(
        typeNormal = Color.Unspecified,
        onTypeNormal = Color.Unspecified,
        typeFighting = Color.Unspecified,
        onTypeFighting = Color.Unspecified,
        typeFlying = Color.Unspecified,
        onTypeFlying = Color.Unspecified,
        typePoison = Color.Unspecified,
        onTypePoison = Color.Unspecified,
        typeGround = Color.Unspecified,
        onTypeGround = Color.Unspecified,
        typeRock = Color.Unspecified,
        onTypeRock = Color.Unspecified,
        typeBug = Color.Unspecified,
        onTypeBug = Color.Unspecified,
        typeGhost = Color.Unspecified,
        onTypeGhost = Color.Unspecified,
        typeSteel = Color.Unspecified,
        onTypeSteel = Color.Unspecified,
        typeFire = Color.Unspecified,
        onTypeFire = Color.Unspecified,
        typeWater = Color.Unspecified,
        onTypeWater = Color.Unspecified,
        typeGrass = Color.Unspecified,
        onTypeGrass = Color.Unspecified,
        typeElectric = Color.Unspecified,
        onTypeElectric = Color.Unspecified,
        typePsychic = Color.Unspecified,
        onTypePsychic = Color.Unspecified,
        typeIce = Color.Unspecified,
        onTypeIce = Color.Unspecified,
        typeDragon = Color.Unspecified,
        onTypeDragon = Color.Unspecified,
        typeDark = Color.Unspecified,
        onTypeDark = Color.Unspecified,
        typeFairy = Color.Unspecified,
        onTypeFairy = Color.Unspecified,
        typeUnknown = Color.Unspecified,
        onTypeUnknown = Color.Unspecified,
        typeNormalContainer = Color.Unspecified,
        onTypeNormalContainer = Color.Unspecified,
        typeFightingContainer = Color.Unspecified,
        onTypeFightingContainer = Color.Unspecified,
        typeFlyingContainer = Color.Unspecified,
        onTypeFlyingContainer = Color.Unspecified,
        typePoisonContainer = Color.Unspecified,
        onTypePoisonContainer = Color.Unspecified,
        typeGroundContainer = Color.Unspecified,
        onTypeGroundContainer = Color.Unspecified,
        typeRockContainer = Color.Unspecified,
        onTypeRockContainer = Color.Unspecified,
        typeBugContainer = Color.Unspecified,
        onTypeBugContainer = Color.Unspecified,
        typeGhostContainer = Color.Unspecified,
        onTypeGhostContainer = Color.Unspecified,
        typeSteelContainer = Color.Unspecified,
        onTypeSteelContainer = Color.Unspecified,
        typeFireContainer = Color.Unspecified,
        onTypeFireContainer = Color.Unspecified,
        typeWaterContainer = Color.Unspecified,
        onTypeWaterContainer = Color.Unspecified,
        typeGrassContainer = Color.Unspecified,
        onTypeGrassContainer = Color.Unspecified,
        typeElectricContainer = Color.Unspecified,
        onTypeElectricContainer = Color.Unspecified,
        typePsychicContainer = Color.Unspecified,
        onTypePsychicContainer = Color.Unspecified,
        typeIceContainer = Color.Unspecified,
        onTypeIceContainer = Color.Unspecified,
        typeDragonContainer = Color.Unspecified,
        onTypeDragonContainer = Color.Unspecified,
        typeDarkContainer = Color.Unspecified,
        onTypeDarkContainer = Color.Unspecified,
        typeFairyContainer = Color.Unspecified,
        onTypeFairyContainer = Color.Unspecified,
        typeUnknownContainer = Color.Unspecified,
        onTypeUnknownContainer = Color.Unspecified
    )
}

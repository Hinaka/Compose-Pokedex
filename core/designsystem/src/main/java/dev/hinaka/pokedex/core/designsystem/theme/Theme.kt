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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint
)

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint
)

private val LightPokedexColors = PokedexColors(
    typeNormal = light_typeNormal,
    onTypeNormal = light_onTypeNormal,
    typeFighting = light_typeFighting,
    onTypeFighting = light_onTypeFighting,
    typeFlying = light_typeFlying,
    onTypeFlying = light_onTypeFlying,
    typePoison = light_typePoison,
    onTypePoison = light_onTypePoison,
    typeGround = light_typeGround,
    onTypeGround = light_onTypeGround,
    typeRock = light_typeRock,
    onTypeRock = light_onTypeRock,
    typeBug = light_typeBug,
    onTypeBug = light_onTypeBug,
    typeGhost = light_typeGhost,
    onTypeGhost = light_onTypeGhost,
    typeSteel = light_typeSteel,
    onTypeSteel = light_onTypeSteel,
    typeFire = light_typeFire,
    onTypeFire = light_onTypeFire,
    typeWater = light_typeWater,
    onTypeWater = light_onTypeWater,
    typeGrass = light_typeGrass,
    onTypeGrass = light_onTypeGrass,
    typeElectric = light_typeElectric,
    onTypeElectric = light_onTypeElectric,
    typePsychic = light_typePsychic,
    onTypePsychic = light_onTypePsychic,
    typeIce = light_typeIce,
    onTypeIce = light_onTypeIce,
    typeDragon = light_typeDragon,
    onTypeDragon = light_onTypeDragon,
    typeDark = light_typeDark,
    onTypeDark = light_onTypeDark,
    typeFairy = light_typeFairy,
    onTypeFairy = light_onTypeFairy,
    typeUnknown = md_theme_light_surface,
    onTypeUnknown = md_theme_light_onSurface,
    typeNormalContainer = light_typeNormalContainer,
    onTypeNormalContainer = light_onTypeNormalContainer,
    typeFightingContainer = light_typeFightingContainer,
    onTypeFightingContainer = light_onTypeFightingContainer,
    typeFlyingContainer = light_typeFlyingContainer,
    onTypeFlyingContainer = light_onTypeFlyingContainer,
    typePoisonContainer = light_typePoisonContainer,
    onTypePoisonContainer = light_onTypePoisonContainer,
    typeGroundContainer = light_typeGroundContainer,
    onTypeGroundContainer = light_onTypeGroundContainer,
    typeRockContainer = light_typeRockContainer,
    onTypeRockContainer = light_onTypeRockContainer,
    typeBugContainer = light_typeBugContainer,
    onTypeBugContainer = light_onTypeBugContainer,
    typeGhostContainer = light_typeGhostContainer,
    onTypeGhostContainer = light_onTypeGhostContainer,
    typeSteelContainer = light_typeSteelContainer,
    onTypeSteelContainer = light_onTypeSteelContainer,
    typeFireContainer = light_typeFireContainer,
    onTypeFireContainer = light_onTypeFireContainer,
    typeWaterContainer = light_typeWaterContainer,
    onTypeWaterContainer = light_onTypeWaterContainer,
    typeGrassContainer = light_typeGrassContainer,
    onTypeGrassContainer = light_onTypeGrassContainer,
    typeElectricContainer = light_typeElectricContainer,
    onTypeElectricContainer = light_onTypeElectricContainer,
    typePsychicContainer = light_typePsychicContainer,
    onTypePsychicContainer = light_onTypePsychicContainer,
    typeIceContainer = light_typeIceContainer,
    onTypeIceContainer = light_onTypeIceContainer,
    typeDragonContainer = light_typeDragonContainer,
    onTypeDragonContainer = light_onTypeDragonContainer,
    typeDarkContainer = light_typeDarkContainer,
    onTypeDarkContainer = light_onTypeDarkContainer,
    typeFairyContainer = light_typeFairyContainer,
    onTypeFairyContainer = light_onTypeFairyContainer,
    typeUnknownContainer = md_theme_light_surfaceVariant,
    onTypeUnknownContainer = md_theme_light_onSurfaceVariant,
    male = light_male,
    onMale = light_onMale,
    female = light_female,
    onFemale = light_onFemale
)

private val DarkPokedexColors = PokedexColors(
    typeNormal = dark_typeNormal,
    onTypeNormal = dark_onTypeNormal,
    typeFighting = dark_typeFighting,
    onTypeFighting = dark_onTypeFighting,
    typeFlying = dark_typeFlying,
    onTypeFlying = dark_onTypeFlying,
    typePoison = dark_typePoison,
    onTypePoison = dark_onTypePoison,
    typeGround = dark_typeGround,
    onTypeGround = dark_onTypeGround,
    typeRock = dark_typeRock,
    onTypeRock = dark_onTypeRock,
    typeBug = dark_typeBug,
    onTypeBug = dark_onTypeBug,
    typeGhost = dark_typeGhost,
    onTypeGhost = dark_onTypeGhost,
    typeSteel = dark_typeSteel,
    onTypeSteel = dark_onTypeSteel,
    typeFire = dark_typeFire,
    onTypeFire = dark_onTypeFire,
    typeWater = dark_typeWater,
    onTypeWater = dark_onTypeWater,
    typeGrass = dark_typeGrass,
    onTypeGrass = dark_onTypeGrass,
    typeElectric = dark_typeElectric,
    onTypeElectric = dark_onTypeElectric,
    typePsychic = dark_typePsychic,
    onTypePsychic = dark_onTypePsychic,
    typeIce = dark_typeIce,
    onTypeIce = dark_onTypeIce,
    typeDragon = dark_typeDragon,
    onTypeDragon = dark_onTypeDragon,
    typeDark = dark_typeDark,
    onTypeDark = dark_onTypeDark,
    typeFairy = dark_typeFairy,
    onTypeFairy = dark_onTypeFairy,
    typeUnknown = md_theme_dark_surface,
    onTypeUnknown = md_theme_dark_onSurface,
    typeNormalContainer = dark_typeNormalContainer,
    onTypeNormalContainer = dark_onTypeNormalContainer,
    typeFightingContainer = dark_typeFightingContainer,
    onTypeFightingContainer = dark_onTypeFightingContainer,
    typeFlyingContainer = dark_typeFlyingContainer,
    onTypeFlyingContainer = dark_onTypeFlyingContainer,
    typePoisonContainer = dark_typePoisonContainer,
    onTypePoisonContainer = dark_onTypePoisonContainer,
    typeGroundContainer = dark_typeGroundContainer,
    onTypeGroundContainer = dark_onTypeGroundContainer,
    typeRockContainer = dark_typeRockContainer,
    onTypeRockContainer = dark_onTypeRockContainer,
    typeBugContainer = dark_typeBugContainer,
    onTypeBugContainer = dark_onTypeBugContainer,
    typeGhostContainer = dark_typeGhostContainer,
    onTypeGhostContainer = dark_onTypeGhostContainer,
    typeSteelContainer = dark_typeSteelContainer,
    onTypeSteelContainer = dark_onTypeSteelContainer,
    typeFireContainer = dark_typeFireContainer,
    onTypeFireContainer = dark_onTypeFireContainer,
    typeWaterContainer = dark_typeWaterContainer,
    onTypeWaterContainer = dark_onTypeWaterContainer,
    typeGrassContainer = dark_typeGrassContainer,
    onTypeGrassContainer = dark_onTypeGrassContainer,
    typeElectricContainer = dark_typeElectricContainer,
    onTypeElectricContainer = dark_onTypeElectricContainer,
    typePsychicContainer = dark_typePsychicContainer,
    onTypePsychicContainer = dark_onTypePsychicContainer,
    typeIceContainer = dark_typeIceContainer,
    onTypeIceContainer = dark_onTypeIceContainer,
    typeDragonContainer = dark_typeDragonContainer,
    onTypeDragonContainer = dark_onTypeDragonContainer,
    typeDarkContainer = dark_typeDarkContainer,
    onTypeDarkContainer = dark_onTypeDarkContainer,
    typeFairyContainer = dark_typeFairyContainer,
    onTypeFairyContainer = dark_onTypeFairyContainer,
    typeUnknownContainer = md_theme_dark_surfaceVariant,
    onTypeUnknownContainer = md_theme_dark_onSurfaceVariant,
    male = dark_male,
    onMale = dark_onMale,
    female = dark_female,
    onFemale = dark_onFemale
)

@Composable
fun PokedexTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    val pokedexColors = if (!useDarkTheme) {
        LightPokedexColors
    } else {
        DarkPokedexColors
    }

    CompositionLocalProvider(LocalPokedexColors provides pokedexColors) {
        MaterialTheme(
            colorScheme = colors,
            typography = PokedexTypography,
            content = content
        )
    }
}

object PokedexTheme {
    val colors: PokedexColors
        @Composable get() = LocalPokedexColors.current
}

val ColorScheme.surfaceOverlay get() = surface.copy(alpha = 0.4f)

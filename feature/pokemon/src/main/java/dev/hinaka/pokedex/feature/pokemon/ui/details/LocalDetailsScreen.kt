package dev.hinaka.pokedex.feature.pokemon.ui.details

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class DetailsTheme(
    val primaryColor: Color = Color.White,
    val onPrimaryColor: Color = Color.Black
)

val LocalDetailsTheme = compositionLocalOf { DetailsTheme() }
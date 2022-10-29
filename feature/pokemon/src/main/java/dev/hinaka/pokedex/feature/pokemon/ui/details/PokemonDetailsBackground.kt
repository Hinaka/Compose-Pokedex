package dev.hinaka.pokedex.feature.pokemon.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import dev.hinaka.pokedex.core.ui.type.getTypeContainerColors
import dev.hinaka.pokedex.domain.pokemon.Pokemon

@Composable
fun DetailsThemeProvider(
    pokemon: Pokemon,
    content: @Composable () -> Unit,
) {
    val (containerColor, contentColor) = pokemon.types.getTypeContainerColors()

    val detailsTheme = DetailsTheme(
        primaryColor = containerColor,
        onPrimaryColor = contentColor
    )

    CompositionLocalProvider(LocalDetailsTheme provides detailsTheme) {
        content()
    }
}
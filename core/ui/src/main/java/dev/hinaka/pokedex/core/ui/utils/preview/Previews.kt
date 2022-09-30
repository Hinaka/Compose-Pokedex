package dev.hinaka.pokedex.core.ui.utils.preview

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@ThemePreviews
annotation class PokedexPreviews

@Preview(
    name = "light theme",
    group = "themes",
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "dark theme",
    group = "themes",
    uiMode = UI_MODE_NIGHT_YES
)
annotation class ThemePreviews
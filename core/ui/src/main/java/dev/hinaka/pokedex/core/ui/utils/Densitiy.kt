package dev.hinaka.pokedex.core.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

val TextUnit.dp: Dp
    @Composable get() = with(LocalDensity.current) {
        this@dp.toDp()
    }
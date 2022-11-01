package dev.hinaka.pokedex.core.ui.utils

import java.text.DecimalFormat

fun Float.roundDecimal(): String {
    val df = DecimalFormat("#.##")
    return df.format(this)
}
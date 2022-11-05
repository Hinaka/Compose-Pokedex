package dev.hinaka.pokedex.core.ui.utils

import java.text.DecimalFormat

fun Number.decimalFormat(): String {
    val df = DecimalFormat("#,###.##")
    return df.format(this)
}
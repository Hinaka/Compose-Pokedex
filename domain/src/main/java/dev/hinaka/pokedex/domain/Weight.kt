package dev.hinaka.pokedex.domain

@JvmInline
value class Weight private constructor(
    val g: Int,
) {
    val hg get() = g / 100f
    val kg get() = g / 1000f

    companion object {
        fun gram(g: Int) = Weight(g)
        fun hectogram(hg: Int) = Weight(hg * 100)
        fun kilogram(kg: Int) = Weight(kg * 1000)
    }
}

val EmptyWeight = Weight.gram(-1)
package dev.hinaka.pokedex.domain.pokemon

@JvmInline
value class Height private constructor(
    val centimeter: Int
) {
    val decimeter get() = centimeter / 10f
    val meter get() = centimeter / 100f

    companion object {
        fun centimeter(cm: Int) = Height(cm)
        fun decimeter(dm: Int) = Height(dm * 10)
        fun meter(m: Int) = Height(m * 100)
    }
}

val EmptyHeight = Height.centimeter(-1)
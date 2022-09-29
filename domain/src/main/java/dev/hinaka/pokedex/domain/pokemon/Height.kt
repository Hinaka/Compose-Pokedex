package dev.hinaka.pokedex.domain.pokemon

@JvmInline
value class Height private constructor(
    val cm: Int
) {
    val dm get() = cm / 10f
    val m get() = cm / 100f

    companion object {
        fun centimeter(cm: Int) = Height(cm)
        fun decimeter(dm: Int) = Height(dm * 10)
        fun meter(m: Int) = Height(m * 100)
    }
}

val EmptyHeight = Height.centimeter(-1)
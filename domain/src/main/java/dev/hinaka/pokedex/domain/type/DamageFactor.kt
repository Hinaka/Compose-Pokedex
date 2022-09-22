package dev.hinaka.pokedex.domain.type

@JvmInline
value class DamageFactor(val value: Int) {
    override fun toString(): String {
        return value.toString()
    }

    operator fun times(other: DamageFactor): DamageFactor {
        return DamageFactor(
            value = (value * other.value) / 100
        )
    }


}


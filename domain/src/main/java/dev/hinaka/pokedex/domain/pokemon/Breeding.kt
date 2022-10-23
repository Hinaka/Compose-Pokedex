package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.pokemon.GenderRatio.GENDERLESS

data class Breeding(
    val genderRatio: GenderRatio,
    val eggGroups: List<EggGroup>,
    val eggCycles: Int,
) {
    val steps get() = eggCycles * 255
}

val EmptyBreeding = Breeding(
    genderRatio = GENDERLESS,
    eggGroups = emptyList(),
    eggCycles = -1
)
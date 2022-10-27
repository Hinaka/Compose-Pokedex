package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.Id

data class GrowthRate(
    val id: Id,
    val description: String,
    val maxExp: Int,
)

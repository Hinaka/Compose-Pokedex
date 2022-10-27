package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.Id

data class Training(
    val effort: Stats,
    val catchRate: Int,
    val baseExp: Int,
    val baseHappiness: Int,
    val growthRate: GrowthRate,
)

val EmptyTraining = Training(
    effort = EmptyStats,
    catchRate = 0,
    baseExp = 0,
    baseHappiness = 0,
    growthRate = GrowthRate(
        id = Id(-1),
        description = "",
        maxExp = 0
    )
)

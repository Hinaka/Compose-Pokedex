package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.Ability
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.move.LearnableMove
import dev.hinaka.pokedex.domain.type.Type

data class Pokemon(
    val id: Id,
    val name: String,
    val types: List<Type>,
    val genus: String,
    val abilities: List<Ability>,
    val species: Species,
    val baseStats: Stats,
    val learnableMoves: List<LearnableMove>,
    val training: Training,
    val breeding: Breeding,
    val imageUrls: ImageUrls,
) {
    data class Species(
        val flavorText: String,
        val height: Height,
        val weight: Weight,
    )

    data class Training(
        val effortYield: Stats,
        val catchRate: Int,
        val growthRate: GrowthRate,
        val baseHappiness: Int,
        val baseExp: Int,
    ) {
        data class GrowthRate(
            val name: String,
            val expToMaxLevel: Int,
        )
    }

    data class Breeding(
        val genderRatio: GenderRatio,
        val eggGroups: List<EggGroup>,
        val eggCycles: Int,
    ) {
        val stepsToHatch get() = eggCycles * 255

        enum class GenderRatio {
            MALE_ONLY,
            ONE_MALE_SEVEN_FEMALE,
            ONE_MALE_THREE_FEMALE,
            HALF_HALF,
            THREE_MALE_ONE_FEMALE,
            SEVEN_MALE_ONE_FEMALE,
            FEMALE_ONLY,
            GENDERLESS,
        }
    }

    data class ImageUrls(
        val officialArtwork: String,
        val frontDefault: String,
        val frontShiny: String,
        val backDefault: String,
        val backShiny: String
    )
}

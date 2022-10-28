package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.Ability
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.move.LearnableMove
import dev.hinaka.pokedex.domain.type.Type
import kotlin.math.floor

data class Pokemon(
    val id: Id,
    val name: String,
    val types: List<Type>,
    val genus: String,
    val abilities: Abilities,
    val species: Species,
    val baseStats: Stats,
    val learnableMoves: List<LearnableMove>,
    val training: Training,
    val breeding: Breeding,
    val imageUrls: ImageUrls,
) {

    data class Abilities(
        val normalAbilities: List<Ability>,
        val hiddenAbility: Ability?
    )

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
        val catchRatePercentAtFullHp get() = catchRate.toFloat() / 3 / 255 * 100

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
    }

    data class ImageUrls(
        val officialArtwork: String,
        val frontDefault: String,
        val frontShiny: String,
        val backDefault: String,
        val backShiny: String
    )
}

val Pokemon.minStats
    get() = with(baseStats) {
        val level = 100
        val iv = 0
        val ev = 0
        val nature = 0.9f

        Stats(
            hp = calculateHp(hp, iv, ev, level),
            attack = calculateStat(attack, iv, ev, level, nature),
            defense = calculateStat(defense, iv, ev, level, nature),
            specialAttack = calculateStat(specialAttack, iv, ev, level, nature),
            specialDefense = calculateStat(specialDefense, iv, ev, level, nature),
            speed = calculateStat(speed, iv, ev, level, nature)
        )
    }

val Pokemon.maxStats
    get() = with(baseStats) {
        val level = 100
        val iv = 31
        val ev = 252
        val nature = 1.1f

        Stats(
            hp = calculateHp(hp, iv, ev, level),
            attack = calculateStat(attack, iv, ev, level, nature),
            defense = calculateStat(defense, iv, ev, level, nature),
            specialAttack = calculateStat(specialAttack, iv, ev, level, nature),
            specialDefense = calculateStat(specialDefense, iv, ev, level, nature),
            speed = calculateStat(speed, iv, ev, level, nature)
        )
    }

private fun calculateHp(
    base: Int,
    iv: Int,
    ev: Int,
    level: Int
): Int {
    return floor((((iv + (2 * base) + (ev / 4f))) * level / 100f) + 10 + level).toInt()
}

private fun calculateStat(
    base: Int,
    iv: Int,
    ev: Int,
    level: Int,
    nature: Float
): Int {
    return floor(((((iv + (2 * base) + (ev / 4f))) * level / 100f) + 5) * nature).toInt()
}


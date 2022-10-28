/*
 * Copyright 2022 Hinaka (Trung Nguyễn Minh Trần)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.Ability
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.move.LearnableMove
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Stats
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
    val imageUrls: ImageUrls
) {

    data class Stats(
        val hp: Int,
        val attack: Int,
        val defense: Int,
        val specialAttack: Int,
        val specialDefense: Int,
        val speed: Int
    ) {
        val total get() = hp + attack + defense + specialAttack + specialDefense + speed

        val max get() = maxOf(hp, attack, defense, specialAttack, specialDefense, speed)
    }

    data class Abilities(
        val normalAbilities: List<Ability>,
        val hiddenAbility: Ability?
    )

    data class Species(
        val flavorText: String,
        val height: Height,
        val weight: Weight
    ) {
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

        @JvmInline
        value class Weight private constructor(
            val g: Int
        ) {
            val hg get() = g / 100f
            val kg get() = g / 1000f

            companion object {
                fun gram(g: Int) = Weight(g)
                fun hectogram(hg: Int) = Weight(hg * 100)
                fun kilogram(kg: Int) = Weight(kg * 1000)
            }
        }
    }

    data class Training(
        val effortYield: Stats,
        val catchRate: Int,
        val growthRate: GrowthRate,
        val baseHappiness: Int,
        val baseExp: Int
    ) {
        val catchRatePercentAtFullHp get() = catchRate.toFloat() / 3 / 255 * 100

        data class GrowthRate(
            val name: String,
            val expToMaxLevel: Int
        )
    }

    data class Breeding(
        val genderRatio: GenderRatio,
        val eggGroups: List<EggGroup>,
        val eggCycles: Int
    ) {
        val stepsToHatch get() = eggCycles * 255

        enum class GenderRatio {
            MALE_ONLY,
            M1_F7,
            M1_F3,
            M1_F1,
            M3_F1,
            M7_F1,
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

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
import dev.hinaka.pokedex.domain.EmptyAbility
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.move.Move
import dev.hinaka.pokedex.domain.type.Type
import kotlin.math.floor

data class Pokemon(
    val id: Id,
    val name: String = "",
    val types: List<Type> = emptyList(),
    val imageUrl: String = "",
    val normalAbilities: List<Ability> = emptyList(),
    val hiddenAbility: Ability = EmptyAbility,
    val baseStats: Stats = EmptyStats,
    val learnableMoves: List<Move> = emptyList(),
    val flavorText: String = "",
    val height: Height = EmptyHeight,
    val weight: Weight = EmptyWeight
)

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

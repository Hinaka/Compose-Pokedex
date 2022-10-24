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
package dev.hinaka.pokedex.data.network.model

import dev.hinaka.pokedex.domain.move.LearnMethod

data class NetworkPokemon(
    val id: Int,
    val name: String?,
    val typeIds: List<Int>?,
    val imageUrl: String?,
    val flavorText: String?,
    val height: Int?,
    val weight: Int?,
    val normalAbilityIds: List<Int>?,
    val hiddenAbilityId: Int?,
    val baseHp: Int?,
    val baseAttack: Int?,
    val baseDefense: Int?,
    val baseSpAttack: Int?,
    val baseSpDefense: Int?,
    val baseSpeed: Int?,
    val learnableMoves: List<LearnableMoves>?,
    val genus: String?,
    val genderRatio: Int?,
    val eggCycles: Int?,
    val eggGroupIds: List<Int>?,
) {
    data class LearnableMoves(
        val moveId: Int?,
        val learnLevel: Int?,
        val learnMethod: LearnMethod?,
    )
}

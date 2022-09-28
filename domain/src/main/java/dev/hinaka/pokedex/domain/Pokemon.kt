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
package dev.hinaka.pokedex.domain

import dev.hinaka.pokedex.domain.type.Type

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
    val weight: Weight = EmptyWeight,
) {
    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other is Pokemon) return this.id == other.id
        return super.equals(other)
    }
}

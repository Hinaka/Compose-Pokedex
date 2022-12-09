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
package dev.hinaka.pokedex.data.repository.mapper

import dev.hinaka.pokedex.data.database.model.move.MoveEntity
import dev.hinaka.pokedex.data.network.model.NetworkMove
import dev.hinaka.pokedex.domain.move.DamageClass.PHYSICAL
import dev.hinaka.pokedex.domain.move.DamageClass.SPECIAL
import dev.hinaka.pokedex.domain.move.DamageClass.STATUS

fun NetworkMove.toEntity(paged: Boolean = false) = MoveEntity(
    id = id,
    name = name,
    typeId = typeId,
    damageClass = getDamageClassType(damageClassId),
    power = power,
    acc = accuracy,
    pp = pp,
    paged = paged,
)

private fun getDamageClassType(id: Int?) = when (id) {
    1 -> STATUS
    2 -> PHYSICAL
    3 -> SPECIAL
    else -> null
}

fun List<NetworkMove>.toEntity() = map { it.toEntity() }

fun List<NetworkMove>.toPagedEntity() = map { it.toEntity(paged = true) }

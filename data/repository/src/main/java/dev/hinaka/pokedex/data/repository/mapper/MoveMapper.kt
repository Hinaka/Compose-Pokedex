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
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.move.Move

fun MoveEntity.toDomain() = Move(
    id = Id(id),
    name = name.orEmpty(),
    typeIdentifier = typeIdentifier!!,
    damageClass = damageClass!!,
    power = power ?: 0,
    acc = acc ?: 0,
    pp = pp ?: 0
)

fun NetworkMove.toEntity() = MoveEntity(
    id = id ?: -1,
    name = name,
    typeIdentifier = domainType,
    damageClass = domainDamageClass,
    power = power,
    acc = accuracy,
    pp = pp
)

fun List<MoveEntity>.toDomain() = map { it.toDomain() }

fun List<NetworkMove>.toEntity() = map { it.toEntity() }

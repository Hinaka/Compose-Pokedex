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
package dev.hinaka.pokedex.data.database.model.move

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.move.DamageClass
import dev.hinaka.pokedex.domain.move.Move
import dev.hinaka.pokedex.domain.type.TypeIdentifier

@Entity(tableName = "moves")
data class MoveEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "type") val typeIdentifier: TypeIdentifier?,
    @ColumnInfo(name = "damage_class") val damageClass: DamageClass?,
    @ColumnInfo(name = "power") val power: Int?,
    @ColumnInfo(name = "acc") val acc: Int?,
    @ColumnInfo(name = "pp") val pp: Int?,
    @ColumnInfo(name = "paged") val paged: Boolean?,
)

fun MoveEntity.toMove() = Move(
    id = Id(id),
    name = name.orEmpty(),
    typeIdentifier = typeIdentifier!!,
    damageClass = damageClass!!,
    power = power ?: 0,
    acc = acc ?: 0,
    pp = pp ?: 0
)

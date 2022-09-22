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

import dev.hinaka.pokedex.data.database.model.type.TypeDamageRelationEntity
import dev.hinaka.pokedex.data.database.model.type.TypeEntity
import dev.hinaka.pokedex.data.network.model.NetworkType
import dev.hinaka.pokedex.data.network.model.common.id
import dev.hinaka.pokedex.domain.type.Type.Identifier.BUG
import dev.hinaka.pokedex.domain.type.Type.Identifier.DARK
import dev.hinaka.pokedex.domain.type.Type.Identifier.DRAGON
import dev.hinaka.pokedex.domain.type.Type.Identifier.ELECTRIC
import dev.hinaka.pokedex.domain.type.Type.Identifier.FAIRY
import dev.hinaka.pokedex.domain.type.Type.Identifier.FIGHTING
import dev.hinaka.pokedex.domain.type.Type.Identifier.FIRE
import dev.hinaka.pokedex.domain.type.Type.Identifier.FLYING
import dev.hinaka.pokedex.domain.type.Type.Identifier.GHOST
import dev.hinaka.pokedex.domain.type.Type.Identifier.GRASS
import dev.hinaka.pokedex.domain.type.Type.Identifier.GROUND
import dev.hinaka.pokedex.domain.type.Type.Identifier.ICE
import dev.hinaka.pokedex.domain.type.Type.Identifier.NORMAL
import dev.hinaka.pokedex.domain.type.Type.Identifier.POISON
import dev.hinaka.pokedex.domain.type.Type.Identifier.PSYCHIC
import dev.hinaka.pokedex.domain.type.Type.Identifier.ROCK
import dev.hinaka.pokedex.domain.type.Type.Identifier.STEEL
import dev.hinaka.pokedex.domain.type.Type.Identifier.WATER

fun NetworkType.toEntity() = id?.let {
    TypeEntity(
        id = it,
        identifier = when (id) {
            1 -> NORMAL
            2 -> FIGHTING
            3 -> FLYING
            4 -> POISON
            5 -> GROUND
            6 -> ROCK
            7 -> BUG
            8 -> GHOST
            9 -> STEEL
            10 -> FIRE
            11 -> WATER
            12 -> GRASS
            13 -> ELECTRIC
            14 -> PSYCHIC
            15 -> ICE
            16 -> DRAGON
            17 -> DARK
            18 -> FAIRY
            else -> null
        },
        name = name
    )
}

fun NetworkType.toDamageTakenMap(): Map<Int, Int>? = id?.let {
    val damageTakenMap = mutableMapOf<Int, Int>()

    damageRelations?.apply {
        doubleDamageFrom.orEmpty().forEach {
            val targetId = it.id
            if (targetId != null) {
                damageTakenMap[targetId] = 200
            }
        }

        halfDamageFrom.orEmpty().forEach {
            val targetId = it.id
            if (targetId != null) {
                damageTakenMap[targetId] = 50
            }
        }

        noDamageFrom.orEmpty().forEach {
            val targetId = it.id
            if (targetId != null) {
                damageTakenMap[targetId] = 0
            }
        }
    }

    damageTakenMap
}

fun List<NetworkType>.toEntity() = mapNotNull { it.toEntity() }
fun List<NetworkType>.toDamageRelationEntity(): List<TypeDamageRelationEntity> {
    val baseDamageTakenMap = mapNotNull { it.id }.associateWith { 100 }

    return flatMap { networkType ->
        val targetTypeId = networkType.id
        val damageTakenMap = baseDamageTakenMap + networkType.toDamageTakenMap().orEmpty()
        damageTakenMap.mapNotNull { (typeId, damageFactor) ->
            targetTypeId?.let {
                TypeDamageRelationEntity(
                    damageTypeId = typeId,
                    targetTypeId = it,
                    damageFactor = damageFactor
                )
            }
        }
    }
}

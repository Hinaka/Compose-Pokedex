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

import dev.hinaka.pokedex.data.network.model.NetworkItem.Name
import dev.hinaka.pokedex.data.network.model.NetworkPagedResponse.NetworkPagedItem
import dev.hinaka.pokedex.domain.DamageClass.PHYSICAL
import dev.hinaka.pokedex.domain.DamageClass.SPECIAL
import dev.hinaka.pokedex.domain.DamageClass.STATUS
import dev.hinaka.pokedex.domain.type.TypeIdentifier.BUG
import dev.hinaka.pokedex.domain.type.TypeIdentifier.DARK
import dev.hinaka.pokedex.domain.type.TypeIdentifier.DRAGON
import dev.hinaka.pokedex.domain.type.TypeIdentifier.ELECTRIC
import dev.hinaka.pokedex.domain.type.TypeIdentifier.FAIRY
import dev.hinaka.pokedex.domain.type.TypeIdentifier.FIGHTING
import dev.hinaka.pokedex.domain.type.TypeIdentifier.FIRE
import dev.hinaka.pokedex.domain.type.TypeIdentifier.FLYING
import dev.hinaka.pokedex.domain.type.TypeIdentifier.GHOST
import dev.hinaka.pokedex.domain.type.TypeIdentifier.GRASS
import dev.hinaka.pokedex.domain.type.TypeIdentifier.GROUND
import dev.hinaka.pokedex.domain.type.TypeIdentifier.ICE
import dev.hinaka.pokedex.domain.type.TypeIdentifier.NORMAL
import dev.hinaka.pokedex.domain.type.TypeIdentifier.POISON
import dev.hinaka.pokedex.domain.type.TypeIdentifier.PSYCHIC
import dev.hinaka.pokedex.domain.type.TypeIdentifier.ROCK
import dev.hinaka.pokedex.domain.type.TypeIdentifier.STEEL
import dev.hinaka.pokedex.domain.type.TypeIdentifier.UNKNOWN
import dev.hinaka.pokedex.domain.type.TypeIdentifier.WATER
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMove(
    val id: Int?,
    val names: List<Name>?,
    val type: NetworkPagedItem?,
    val accuracy: Int?,
    val power: Int?,
    val pp: Int?,
    val damage_class: NetworkPagedItem?
) {

    val name get() = names?.first { it.language?.name == "en" }?.name.orEmpty()
    val domainType
        get() = when (type?.id) {
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
            10001 -> UNKNOWN
            else -> null
        }
    val domainDamageClass
        get() = when (damage_class?.id) {
            1 -> STATUS
            2 -> PHYSICAL
            3 -> SPECIAL
            else -> null
        }
}

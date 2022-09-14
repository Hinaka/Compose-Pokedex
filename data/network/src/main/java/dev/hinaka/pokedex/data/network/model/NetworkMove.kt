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
import dev.hinaka.pokedex.domain.Type.BUG
import dev.hinaka.pokedex.domain.Type.DARK
import dev.hinaka.pokedex.domain.Type.DRAGON
import dev.hinaka.pokedex.domain.Type.ELECTRIC
import dev.hinaka.pokedex.domain.Type.FAIRY
import dev.hinaka.pokedex.domain.Type.FIGHTING
import dev.hinaka.pokedex.domain.Type.FIRE
import dev.hinaka.pokedex.domain.Type.FLYING
import dev.hinaka.pokedex.domain.Type.GHOST
import dev.hinaka.pokedex.domain.Type.GRASS
import dev.hinaka.pokedex.domain.Type.GROUND
import dev.hinaka.pokedex.domain.Type.ICE
import dev.hinaka.pokedex.domain.Type.NORMAL
import dev.hinaka.pokedex.domain.Type.POISON
import dev.hinaka.pokedex.domain.Type.PSYCHIC
import dev.hinaka.pokedex.domain.Type.ROCK
import dev.hinaka.pokedex.domain.Type.STEEL
import dev.hinaka.pokedex.domain.Type.UNKNOWN
import dev.hinaka.pokedex.domain.Type.WATER
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

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

import dev.hinaka.pokedex.data.network.model.NetworkPagedResponse.NetworkPagedItem
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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPokemon(
    val id: Int?,
    val name: String?,
    val sprites: Sprites?,
    val types: List<Type>?
) {

    @Serializable
    data class Sprites(
        val other: Other?
    ) {

        @Serializable
        data class Other(
            @SerialName("official-artwork") val officialArtwork: OfficialArtwork?
        ) {

            @Serializable
            data class OfficialArtwork(
                val front_default: String?
            )
        }
    }

    @Serializable
    data class Type(
        val slot: Int?,
        val type: NetworkPagedItem?
    )

    val imageUrl get() = sprites?.other?.officialArtwork?.front_default.orEmpty()
    val domainTypes
        get() = types.orEmpty().mapNotNull { type ->
            when (type.type?.id) {
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
        }
}

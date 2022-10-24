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
package dev.hinaka.pokedex.data.network.response.pokemon

import dev.hinaka.pokedex.data.network.response.common.NameAndUrlResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetPokemonResponse(
    val id: Int?,
    val height: Int?,
    val weight: Int?,
    val types: List<Type>?,
    val abilities: List<Ability>?,
    val moves: List<Move>?,
    val species: NameAndUrlResponse?,
    val stats: List<Stat>?,
    val sprites: Sprites?,
    val name: String?
) {

    @Serializable
    data class Type(
        val type: NameAndUrlResponse?,
        val slot: Int?
    )

    @Serializable
    data class Ability(
        val ability: NameAndUrlResponse?,
        val is_hidden: Boolean?
    )

    @Serializable
    data class Move(
        val move: NameAndUrlResponse?,
        val version_group_details: List<VersionGroupDetail>?
    ) {
        @Serializable
        data class VersionGroupDetail(
            val level_learned_at: Int?,
            val move_learn_method: NameAndUrlResponse?,
            val version_group: NameAndUrlResponse?
        )
    }

    @Serializable
    data class Stat(
        val stat: NameAndUrlResponse?,
        val base_stat: Int?
    )

    @Serializable
    data class Sprites(
        val back_default: String?,
        val back_shiny: String?,
        val front_default: String?,
        val front_shiny: String?,
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
}

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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Deprecated("replace with new NetworkPokemon")
data class NetworkPokemonDep(
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
}

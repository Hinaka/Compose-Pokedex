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

import dev.hinaka.pokedex.data.network.response.common.LanguageResponse
import dev.hinaka.pokedex.data.network.response.common.NameAndUrlResponse
import kotlinx.serialization.Serializable

@Serializable
internal data class GetPokemonSpeciesResponse(
    val names: List<Name>?,
    val genera: List<Genus>?,
    val flavor_text_entries: List<FlavorText>?,
    val gender_rate: Int?,
    val hatch_counter: Int?,
    val egg_groups: List<NameAndUrlResponse>?,
    val capture_rate: Int?,
    val base_happiness: Int?,
    val growth_rate: NameAndUrlResponse?
) {

    @Serializable
    data class Name(
        val language: LanguageResponse?,
        val name: String?
    )

    @Serializable
    data class Genus(
        val language: LanguageResponse?,
        val genus: String?
    )

    @Serializable
    data class FlavorText(
        val language: LanguageResponse?,
        val flavor_text: String?
    )
}

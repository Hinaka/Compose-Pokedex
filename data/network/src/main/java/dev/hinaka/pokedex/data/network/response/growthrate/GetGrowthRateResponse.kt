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
package dev.hinaka.pokedex.data.network.response.growthrate

import dev.hinaka.pokedex.data.network.response.common.LanguageResponse
import kotlinx.serialization.Serializable

@Serializable
internal data class GetGrowthRateResponse(
    val id: Int,
    val descriptions: List<Description>?,
    val levels: List<Level>?
) {
    @Serializable
    data class Description(
        val language: LanguageResponse?,
        val description: String?
    )

    @Serializable
    data class Level(
        val level: Int?,
        val experience: Int?
    )
}

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
import kotlinx.serialization.Serializable

@Serializable
data class NetworkLocation(
    val id: Int?,
    val names: List<Name>?,
    val region: NetworkPagedItem?
) {
    val name get() = names?.first { it.language?.name == "en" }?.name.orEmpty()
    val regionName get() = region?.name.orEmpty()
}

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

import dev.hinaka.pokedex.data.network.model.NetworkItem.EffectEntry
import dev.hinaka.pokedex.data.network.model.NetworkItem.Name
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAbility(
    val id: Int?,
    val names: List<Name>?,
    val effect_entries: List<EffectEntry>?
) {
    val name get() = names?.first { it.language?.name == "en" }?.name.orEmpty()
    val effect get() = effect_entries?.first { it.language?.name == "en" }?.short_effect.orEmpty()
}

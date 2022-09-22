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

import dev.hinaka.pokedex.data.network.model.common.NetworkListItem
import dev.hinaka.pokedex.data.network.model.common.NetworkName
import dev.hinaka.pokedex.data.network.model.common.enName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkType(
    val id: Int?,
    val names: List<NetworkName>?,
    @SerialName("damage_relations") val damageRelations: DamageRelations?
) {
    val name get() = names.enName

    @Serializable
    data class DamageRelations(
        @SerialName("double_damage_from") val doubleDamageFrom: List<NetworkListItem>?,
        @SerialName("double_damage_to") val doubleDamageTo: List<NetworkListItem>?,
        @SerialName("half_damage_from") val halfDamageFrom: List<NetworkListItem>?,
        @SerialName("half_damage_to") val halfDamageTo: List<NetworkListItem>?,
        @SerialName("no_damage_from") val noDamageFrom: List<NetworkListItem>?,
        @SerialName("no_damage_to") val noDamageTo: List<NetworkListItem>?
    )
}

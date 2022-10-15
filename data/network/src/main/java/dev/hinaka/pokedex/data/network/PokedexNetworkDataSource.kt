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
package dev.hinaka.pokedex.data.network

import dev.hinaka.pokedex.data.network.model.NetworkAbility
import dev.hinaka.pokedex.data.network.model.NetworkItem
import dev.hinaka.pokedex.data.network.model.NetworkLocation
import dev.hinaka.pokedex.data.network.model.NetworkMove
import dev.hinaka.pokedex.data.network.model.NetworkNature
import dev.hinaka.pokedex.data.network.model.NetworkPokemon
import dev.hinaka.pokedex.data.network.model.NetworkType

interface PokedexNetworkDataSource {
    suspend fun getPokemons(offset: Int, limit: Int): List<NetworkPokemon>
    suspend fun getItems(offset: Int, limit: Int): List<NetworkItem>
    suspend fun getMoves(offset: Int, limit: Int): List<NetworkMove>
    suspend fun getMoves(ids: List<Int>): List<NetworkMove>
    suspend fun getLocations(offset: Int, limit: Int): List<NetworkLocation>
    suspend fun getAbilities(offset: Int, limit: Int): List<NetworkAbility>
    suspend fun getAbilities(ids: List<Int>): List<NetworkAbility>
    suspend fun getNatures(offset: Int, limit: Int): List<NetworkNature>
    suspend fun getTypes(): List<NetworkType>
}

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
package dev.hinaka.pokedex.data.network.retrofit

import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.network.model.NetworkAbility
import dev.hinaka.pokedex.data.network.model.NetworkItem
import dev.hinaka.pokedex.data.network.model.NetworkLocation
import dev.hinaka.pokedex.data.network.model.NetworkMove
import dev.hinaka.pokedex.data.network.model.NetworkNature
import dev.hinaka.pokedex.data.network.model.NetworkPokemon
import dev.hinaka.pokedex.data.network.model.NetworkType
import dev.hinaka.pokedex.data.network.retrofit.api.AbilityApi
import dev.hinaka.pokedex.data.network.retrofit.api.ItemApi
import dev.hinaka.pokedex.data.network.retrofit.api.LocationApi
import dev.hinaka.pokedex.data.network.retrofit.api.MoveApi
import dev.hinaka.pokedex.data.network.retrofit.api.NatureApi
import dev.hinaka.pokedex.data.network.retrofit.api.PokemonApi
import dev.hinaka.pokedex.data.network.retrofit.api.TypeApi
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class RetrofitPokedexNetworkDataSource @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val itemApi: ItemApi,
    private val moveApi: MoveApi,
    private val locationApi: LocationApi,
    private val abilityApi: AbilityApi,
    private val natureApi: NatureApi,
    private val typeApi: TypeApi
) : PokedexNetworkDataSource {

    override suspend fun getPokemons(offset: Int, limit: Int): List<NetworkPokemon> =
        coroutineScope {
            val ids = pokemonApi.getPokemons(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { pokemonApi.getPokemon(it) }
            }.awaitAll()
        }

    override suspend fun getItems(offset: Int, limit: Int): List<NetworkItem> =
        coroutineScope {
            val ids = itemApi.getItems(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { itemApi.getItem(it) }
            }.awaitAll()
        }

    override suspend fun getMoves(offset: Int, limit: Int): List<NetworkMove> =
        coroutineScope {
            val ids = moveApi.getMoves(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { moveApi.getMove(it) }
            }.awaitAll()
        }

    override suspend fun getLocations(offset: Int, limit: Int): List<NetworkLocation> =
        coroutineScope {
            val ids = locationApi.getLocations(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { locationApi.getLocation(it) }
            }.awaitAll()
        }

    override suspend fun getAbilities(offset: Int, limit: Int): List<NetworkAbility> =
        coroutineScope {
            val ids = abilityApi.getAbilities(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { abilityApi.getAbility(it) }
            }.awaitAll()
        }

    override suspend fun getNatures(offset: Int, limit: Int): List<NetworkNature> =
        coroutineScope {
            val ids = natureApi.getNatures(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { natureApi.getNature(it) }
            }.awaitAll()
        }

    override suspend fun getTypes(): List<NetworkType> =
        coroutineScope {
            val ids = typeApi.getTypes().results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { typeApi.getType(it) }
            }.awaitAll()
        }
}

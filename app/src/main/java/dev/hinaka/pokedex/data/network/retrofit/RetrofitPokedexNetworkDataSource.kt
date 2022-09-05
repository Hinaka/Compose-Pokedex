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
import dev.hinaka.pokedex.data.network.model.NetworkPokemon
import dev.hinaka.pokedex.data.network.retrofit.api.PokemonApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class RetrofitPokedexNetworkDataSource @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokedexNetworkDataSource {

    override suspend fun getPokemons(offset: Int, limit: Int): List<NetworkPokemon> =
        coroutineScope {
            val ids = pokemonApi.getPokemons(
                offset = offset,
                limit = limit,
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { pokemonApi.getPokemon(it) }
            }.awaitAll()
        }
}

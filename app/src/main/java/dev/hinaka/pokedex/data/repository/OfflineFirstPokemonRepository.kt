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
package dev.hinaka.pokedex.data.repository

import dev.hinaka.pokedex.data.database.dao.PokemonDao
import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.repository.mapper.toDomain
import dev.hinaka.pokedex.data.repository.mapper.toEntity
import dev.hinaka.pokedex.domain.Pokemon
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class OfflineFirstPokemonRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val networkDataSource: PokedexNetworkDataSource
) : PokemonRepository {

    override fun getPokemonsStream(): Flow<List<Pokemon>> {
        return pokemonDao.getPokemonEntitiesStream()
            .map { it.toDomain() }
            .onEach {
                if (it.isEmpty()) {
                    fetchPokemonsFromNetwork()
                }
            }
    }

    private suspend fun fetchPokemonsFromNetwork() {
        val networkPokemons = networkDataSource.getPokemons()
        pokemonDao.insertOrReplacesPokemons(networkPokemons.toEntity())
    }
}

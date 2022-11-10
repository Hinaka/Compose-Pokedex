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
package dev.hinaka.pokedex.data.repository.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import dev.hinaka.pokedex.data.database.PokedexDatabase
import dev.hinaka.pokedex.data.database.model.pokemon.PokemonWithTypes
import dev.hinaka.pokedex.data.network.datasource.PokedexNetworkSource
import dev.hinaka.pokedex.data.repository.mapper.toPagedEntity
import dev.hinaka.pokedex.data.repository.mapper.toPokemonEggGroupXRef
import dev.hinaka.pokedex.data.repository.mapper.toPokemonGrowthRateXRef
import dev.hinaka.pokedex.data.repository.mapper.toPokemonMoveXRef
import dev.hinaka.pokedex.data.repository.mapper.toPokemonTypeXRef

private const val LABEL = "pokemon"

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val db: PokedexDatabase,
    private val networkDataSource: PokedexNetworkSource
) : RemoteMediator<Int, PokemonWithTypes>() {

    private val pokemonDao = db.pokemonDao()

    override suspend fun initialize() = remoteKeyInitialize(db, LABEL)

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonWithTypes>
    ) = remoteKeyLoad(
        db = db,
        label = LABEL,
        loadType = loadType,
        state = state,
        networkLoad = { offset, limit ->
            networkDataSource.getPokemons(offset, limit)
        },
        storeLocal = { networkPokemons ->
            with(pokemonDao) {
                insertAll(networkPokemons.toPagedEntity())
                insertAllTypeXRefs(networkPokemons.toPokemonTypeXRef())
                insertAllMoveXRefs(networkPokemons.toPokemonMoveXRef())
                insertAllEggGroupXRefs(networkPokemons.toPokemonEggGroupXRef())
                insertAllGrowthRateXRefs(networkPokemons.toPokemonGrowthRateXRef())
            }
        },
        onRefresh = {
            pokemonDao.clearAll()
        },
        endOfPaginationReached = { networkPokemons -> networkPokemons.isEmpty() },
        nextOffset = { currentOffset, networkPokemons -> currentOffset + networkPokemons.size }
    )
}

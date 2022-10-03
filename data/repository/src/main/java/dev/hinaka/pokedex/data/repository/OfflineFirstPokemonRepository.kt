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

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dev.hinaka.pokedex.data.database.PokedexDatabase
import dev.hinaka.pokedex.data.database.model.pokemon.PokemonDetails
import dev.hinaka.pokedex.data.database.model.pokemon.toDomain
import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.repository.mapper.toEntity
import dev.hinaka.pokedex.data.repository.mediators.PokemonRemoteMediator
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class OfflineFirstPokemonRepository @Inject constructor(
    private val db: PokedexDatabase,
    private val networkDataSource: PokedexNetworkDataSource
) : PokemonRepository {

    private val pokemonDao = db.pokemonDao()
    private val abilityDao = db.abilityDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonPagingStream(pageSize: Int): Flow<PagingData<Pokemon>> {
        val config = PagingConfig(
            pageSize = pageSize
        )
        return Pager(
            config = config,
            remoteMediator = PokemonRemoteMediator(db, networkDataSource)
        ) {
            pokemonDao.pagingSource()
        }.flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override fun getPokemonDetailsStream(id: Id): Flow<Pokemon> {
        return pokemonDao.pokemonDetailsStream(id.value)
            .flatMapLatest {
                flow {
                    Log.d("Trung", "$it")
                    emit(it.toDomain())
                    val missingAbilityIds = mutableListOf<Int>()
                    if (it.ability1 == null) it.pokemon.ability1Id?.let { id ->
                        missingAbilityIds.add(
                            id
                        )
                    }

                    if (it.ability2 == null) it.pokemon.ability2Id?.let { id ->
                        missingAbilityIds.add(
                            id
                        )
                    }

                    if (it.hiddenAbility == null) it.pokemon.hiddenAbilityId?.let { id ->
                        missingAbilityIds.add(
                            id
                        )
                    }

                    val missingAbilities = networkDataSource.getAbilities(missingAbilityIds)
                    abilityDao.insertAll(missingAbilities.toEntity())
                }
            }
    }

    private fun checkAndGetMissingData(pokemonDetails: PokemonDetails) {
    }
}

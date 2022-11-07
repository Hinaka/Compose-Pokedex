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
import dev.hinaka.pokedex.data.database.model.LocationEntity
import dev.hinaka.pokedex.data.network.datasource.PokedexNetworkSource
import dev.hinaka.pokedex.data.repository.mapper.toEntity

private const val LABEL = "location"

@OptIn(ExperimentalPagingApi::class)
class LocationRemoteMediator(
    private val db: PokedexDatabase,
    private val networkDataSource: PokedexNetworkSource
) : RemoteMediator<Int, LocationEntity>() {

    private val locationDao = db.locationDao()

    override suspend fun initialize() = remoteKeyInitialize(db, LABEL)

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocationEntity>
    ) = remoteKeyLoad(
        db = db,
        label = LABEL,
        loadType = loadType,
        state = state,
        networkLoad = { offset, limit -> networkDataSource.getLocations(offset, limit) },
        storeLocal = { networkLocations -> locationDao.insertAll(networkLocations.toEntity()) },
        onRefresh = { locationDao.clearAll() },
        nextOffset = { currentOffset, networkLocations -> currentOffset + networkLocations.size },
        endOfPaginationReached = { networkLocations -> networkLocations.isEmpty() }
    )
}

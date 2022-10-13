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
import androidx.paging.LoadType.APPEND
import androidx.paging.LoadType.PREPEND
import androidx.paging.LoadType.REFRESH
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.hinaka.pokedex.data.database.PokedexDatabase
import dev.hinaka.pokedex.data.database.model.move.MoveEntity
import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.repository.mapper.toEntity

@OptIn(ExperimentalPagingApi::class)
class MoveRemoteMediator(
    private val db: PokedexDatabase,
    private val networkDataSource: PokedexNetworkDataSource
) : RemoteMediator<Int, MoveEntity>() {

    private val moveDao = db.moveDao()

//    override suspend fun initialize(): InitializeAction {
//        return SKIP_INITIAL_REFRESH
//    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MoveEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                REFRESH -> null
                PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)

                    lastItem.id
                }
            }

            val networkMoves = networkDataSource.getMoves(
                offset = loadKey ?: 0,
                limit = state.config.pageSize
            )

            db.withTransaction {
                if (loadType == REFRESH) {
                    moveDao.clearAll()
                }

                moveDao.insertAll(networkMoves.toEntity())
            }

            MediatorResult.Success(endOfPaginationReached = networkMoves.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}

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
import androidx.paging.RemoteMediator.InitializeAction
import androidx.paging.RemoteMediator.MediatorResult
import androidx.room.withTransaction
import dev.hinaka.pokedex.data.database.PokedexDatabase
import dev.hinaka.pokedex.data.database.model.remotekey.RemoteKeyEntity

@OptIn(ExperimentalPagingApi::class)
internal suspend fun remoteKeyInitialize(
    db: PokedexDatabase,
    label: String
) = when (db.remoteKeyDao().remoteKeyByLabel(label)?.nextOffset) {
    null -> InitializeAction.LAUNCH_INITIAL_REFRESH
    else -> InitializeAction.SKIP_INITIAL_REFRESH
}

@OptIn(ExperimentalPagingApi::class)
internal suspend fun <T : Any, R> remoteKeyLoad(
    db: PokedexDatabase,
    label: String,
    loadType: LoadType,
    state: PagingState<Int, T>,
    networkLoad: suspend (offset: Int, limit: Int) -> R,
    storeLocal: suspend (response: R) -> Unit,
    onRefresh: suspend () -> Unit,
    nextOffset: (currentOffset: Int, response: R) -> Int,
    endOfPaginationReached: (response: R) -> Boolean
): MediatorResult {
    return try {
        val remoteKeyDao = db.remoteKeyDao()

        val offset = when (loadType) {
            REFRESH -> 0
            PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            APPEND -> remoteKeyDao.remoteKeyByLabel(label)?.nextOffset
                ?: return MediatorResult.Success(endOfPaginationReached = true)
        }

        val limit = when (loadType) {
            REFRESH -> state.config.initialLoadSize
            else -> state.config.pageSize
        }

        val response = networkLoad(offset, limit)

        db.withTransaction {
            if (loadType == REFRESH) {
                remoteKeyDao.deleteByLabel(label)
                onRefresh()
            }

            remoteKeyDao.insertOrReplace(
                RemoteKeyEntity(
                    label = label,
                    nextOffset = nextOffset(offset, response)
                )
            )

            storeLocal(response)
        }

        MediatorResult.Success(endOfPaginationReached(response))
    } catch (e: Exception) {
        MediatorResult.Error(e)
    }
}

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

import dev.hinaka.pokedex.data.network.api.MoveApi
import dev.hinaka.pokedex.data.network.datasource.MoveNetworkSource
import dev.hinaka.pokedex.data.network.model.NetworkMove
import dev.hinaka.pokedex.data.network.response.common.id
import dev.hinaka.pokedex.data.network.response.common.isEn
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class RetrofitMoveNetworkSource @Inject constructor(
    private val moveApi: MoveApi
) : MoveNetworkSource {
    override suspend fun getMoves(offset: Int, limit: Int): List<NetworkMove> =
        coroutineScope {
            val ids = moveApi.getMoves(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            getMoves(ids)
        }

    override suspend fun getMoves(ids: List<Int>): List<NetworkMove> =
        coroutineScope {
            ids.map {
                async { getMove(it) }
            }.awaitAll()
        }

    private suspend fun getMove(id: Int): NetworkMove {
        val move = moveApi.getMove(id)

        return NetworkMove(
            id = id,
            name = move.names?.firstOrNull { it.language.isEn }?.name,
            accuracy = move.accuracy,
            power = move.power,
            pp = move.pp,
            typeId = move.type?.id,
            damageClassId = move.damage_class?.id
        )
    }
}

package dev.hinaka.pokedex.data.network.retrofit.source

import dev.hinaka.pokedex.data.network.model.NetworkMove
import dev.hinaka.pokedex.data.network.retrofit.api.MoveApi
import dev.hinaka.pokedex.data.network.source.MoveNetworkSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class RetrofitMoveNetworkSource @Inject constructor(
    private val moveApi: MoveApi,
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
                async { moveApi.getMove(it) }
            }.awaitAll()
        }
}
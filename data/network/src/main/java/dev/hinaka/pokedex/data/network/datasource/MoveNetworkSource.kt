package dev.hinaka.pokedex.data.network.datasource

import dev.hinaka.pokedex.data.network.model.NetworkMove

interface MoveNetworkSource {
    suspend fun getMoves(offset: Int, limit: Int): List<NetworkMove>
    suspend fun getMoves(ids: List<Int>): List<NetworkMove>
}
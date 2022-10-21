package dev.hinaka.pokedex.data.network.source

import dev.hinaka.pokedex.data.network.model.NetworkMove

interface MoveNetworkSource {
    suspend fun getMoves(offset: Int, limit: Int): List<NetworkMove>
    suspend fun getMoves(ids: List<Int>): List<NetworkMove>
}
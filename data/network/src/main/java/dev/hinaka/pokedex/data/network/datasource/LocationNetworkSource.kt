package dev.hinaka.pokedex.data.network.datasource

import dev.hinaka.pokedex.data.network.model.NetworkLocation

interface LocationNetworkSource {
    suspend fun getLocations(offset: Int, limit: Int): List<NetworkLocation>
}
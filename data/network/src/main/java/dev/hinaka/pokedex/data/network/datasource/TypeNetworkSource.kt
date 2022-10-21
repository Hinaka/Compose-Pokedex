package dev.hinaka.pokedex.data.network.datasource

import dev.hinaka.pokedex.data.network.model.NetworkType

interface TypeNetworkSource {
    suspend fun getTypes(): List<NetworkType>
}

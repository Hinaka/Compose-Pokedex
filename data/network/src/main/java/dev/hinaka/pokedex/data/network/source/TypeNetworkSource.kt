package dev.hinaka.pokedex.data.network.source

import dev.hinaka.pokedex.data.network.model.NetworkType

interface TypeNetworkSource {
    suspend fun getTypes(): List<NetworkType>
}

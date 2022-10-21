package dev.hinaka.pokedex.data.network.source

import dev.hinaka.pokedex.data.network.model.NetworkNature

interface NatureNetworkSource {
    suspend fun getNatures(offset: Int, limit: Int): List<NetworkNature>
}
package dev.hinaka.pokedex.data.network.source

import dev.hinaka.pokedex.data.network.model.NetworkItem

interface ItemNetworkSource {
    suspend fun getItems(offset: Int, limit: Int): List<NetworkItem>
}
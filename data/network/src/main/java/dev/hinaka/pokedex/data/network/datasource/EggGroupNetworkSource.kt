package dev.hinaka.pokedex.data.network.datasource

import dev.hinaka.pokedex.data.network.model.NetworkEggGroup

interface EggGroupNetworkSource {
    suspend fun getEggGroups(): List<NetworkEggGroup>
}
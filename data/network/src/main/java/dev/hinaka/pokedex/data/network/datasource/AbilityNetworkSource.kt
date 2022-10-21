package dev.hinaka.pokedex.data.network.datasource

import dev.hinaka.pokedex.data.network.model.NetworkAbility

interface AbilityNetworkSource {
    suspend fun getAbilities(offset: Int, limit: Int): List<NetworkAbility>
    suspend fun getAbilities(ids: List<Int>): List<NetworkAbility>
}
package dev.hinaka.pokedex.data.repository

import dev.hinaka.pokedex.data.network.datasource.EggGroupNetworkSource
import javax.inject.Inject

class OfflineFirstEggGroupRepository @Inject constructor(
    private val networkSource: EggGroupNetworkSource,
) : EggGroupRepository {

    override suspend fun syncEggGroups() {
        networkSource.getEggGroups()
    }
}
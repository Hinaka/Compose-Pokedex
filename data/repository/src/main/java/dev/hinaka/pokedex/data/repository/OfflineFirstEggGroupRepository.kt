package dev.hinaka.pokedex.data.repository

import dev.hinaka.pokedex.data.database.PokedexDatabase
import dev.hinaka.pokedex.data.network.datasource.EggGroupNetworkSource
import dev.hinaka.pokedex.data.repository.mapper.toEntity
import javax.inject.Inject

class OfflineFirstEggGroupRepository @Inject constructor(
    private val db: PokedexDatabase,
    private val networkSource: EggGroupNetworkSource,
) : EggGroupRepository {

    private val eggGroupDao = db.eggGroupDao()

    override suspend fun syncEggGroups() {
        val networkEggGroups = networkSource.getEggGroups()
        eggGroupDao.insertAll(networkEggGroups.toEntity())
    }
}
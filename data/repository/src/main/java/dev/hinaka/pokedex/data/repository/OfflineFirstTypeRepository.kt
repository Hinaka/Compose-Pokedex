package dev.hinaka.pokedex.data.repository

import dev.hinaka.pokedex.data.database.PokedexDatabase
import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.repository.mapper.toEntity
import javax.inject.Inject

class OfflineFirstTypeRepository @Inject constructor(
    private val db: PokedexDatabase,
    private val networkDataSource: PokedexNetworkDataSource,
) : TypeRepository {

    private val typeDao = db.typeDao()

    override suspend fun getTypes() {
        val networkTypes = networkDataSource.getTypes()
        typeDao.insertAll(networkTypes.toEntity())
    }
}
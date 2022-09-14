package dev.hinaka.pokedex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dev.hinaka.pokedex.data.database.PokedexDatabase
import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.repository.mapper.toDomain
import dev.hinaka.pokedex.data.repository.mediators.AbilityRemoteMediator
import dev.hinaka.pokedex.domain.Ability
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstAbilityRepository @Inject constructor(
    private val db: PokedexDatabase,
    private val networkDataSource: PokedexNetworkDataSource,
) : AbilityRepository {

    private val abilityDao = db.abilityDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAbilityPagingStream(pageSize: Int): Flow<PagingData<Ability>> {
        val config = PagingConfig(
            pageSize = pageSize
        )
        return Pager(
            config = config,
            remoteMediator = AbilityRemoteMediator(db, networkDataSource)
        ) {
            abilityDao.pagingSource()
        }.flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}
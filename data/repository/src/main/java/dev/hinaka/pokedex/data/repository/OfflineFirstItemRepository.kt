package dev.hinaka.pokedex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dev.hinaka.pokedex.data.database.PokedexDatabase
import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.repository.mapper.toDomain
import dev.hinaka.pokedex.data.repository.mediators.ItemRemoteMediator
import dev.hinaka.pokedex.domain.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstItemRepository @Inject constructor(
    private val db: PokedexDatabase,
    private val networkDataSource: PokedexNetworkDataSource
) : ItemRepository {

    private val itemDao = db.itemDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getItemPagingStream(pageSize: Int): Flow<PagingData<Item>> {
        val config = PagingConfig(
            pageSize = pageSize
        )
        return Pager(
            config = config,
            remoteMediator = ItemRemoteMediator(db, networkDataSource)
        ) {
            itemDao.pagingSource()
        }.flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}
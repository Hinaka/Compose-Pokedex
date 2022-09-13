package dev.hinaka.pokedex.data.repository

import androidx.paging.PagingData
import dev.hinaka.pokedex.domain.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItemPagingStream(pageSize: Int): Flow<PagingData<Item>>
}
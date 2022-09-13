package dev.hinaka.pokedex.feature.item.usecase

import androidx.paging.PagingData
import dev.hinaka.pokedex.data.repository.ItemRepository
import dev.hinaka.pokedex.domain.Item
import kotlinx.coroutines.flow.Flow

typealias GetItemPagingUseCase =
    @JvmSuppressWildcards suspend (pageSize: Int) -> Flow<PagingData<Item>>

fun getItemPaging(
    repository: ItemRepository,
    pageSize: Int
) = repository.getItemPagingStream(pageSize)
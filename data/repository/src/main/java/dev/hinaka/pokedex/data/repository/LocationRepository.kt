package dev.hinaka.pokedex.data.repository

import androidx.paging.PagingData
import dev.hinaka.pokedex.domain.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocationPagingStream(pageSize: Int): Flow<PagingData<Location>>
}
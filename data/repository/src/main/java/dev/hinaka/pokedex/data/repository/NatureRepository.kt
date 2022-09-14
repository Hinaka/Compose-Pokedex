package dev.hinaka.pokedex.data.repository

import androidx.paging.PagingData
import dev.hinaka.pokedex.domain.Nature
import kotlinx.coroutines.flow.Flow

interface NatureRepository {
    fun getNaturePagingStream(pageSize: Int): Flow<PagingData<Nature>>
}
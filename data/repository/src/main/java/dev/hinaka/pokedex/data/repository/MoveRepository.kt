package dev.hinaka.pokedex.data.repository

import androidx.paging.PagingData
import dev.hinaka.pokedex.domain.Move
import kotlinx.coroutines.flow.Flow

interface MoveRepository {
    fun getMovePagingStream(pageSize: Int): Flow<PagingData<Move>>
}
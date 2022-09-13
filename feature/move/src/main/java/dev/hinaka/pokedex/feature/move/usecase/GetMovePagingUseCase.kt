package dev.hinaka.pokedex.feature.move.usecase

import androidx.paging.PagingData
import dev.hinaka.pokedex.data.repository.MoveRepository
import dev.hinaka.pokedex.domain.Move
import kotlinx.coroutines.flow.Flow

typealias GetMovePagingUseCase =
    @JvmSuppressWildcards suspend (pageSize: Int) -> Flow<PagingData<Move>>

fun getMovePaging(
    repository: MoveRepository,
    pageSize: Int,
) = repository.getMovePagingStream(pageSize)
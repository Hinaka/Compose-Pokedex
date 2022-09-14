package dev.hinaka.pokedex.feature.nature.usecase

import androidx.paging.PagingData
import dev.hinaka.pokedex.data.repository.NatureRepository
import dev.hinaka.pokedex.domain.Nature
import kotlinx.coroutines.flow.Flow

typealias GetNaturePagingUseCase =
    @JvmSuppressWildcards (pageSize: Int) -> Flow<PagingData<Nature>>

fun getNaturePaging(
    repository: NatureRepository,
    pageSize: Int,
) = repository.getNaturePagingStream(pageSize)
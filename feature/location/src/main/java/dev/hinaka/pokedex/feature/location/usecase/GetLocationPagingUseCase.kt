package dev.hinaka.pokedex.feature.location.usecase

import androidx.paging.PagingData
import dev.hinaka.pokedex.data.repository.LocationRepository
import dev.hinaka.pokedex.domain.Location
import kotlinx.coroutines.flow.Flow

typealias GetLocationPagingUseCase =
    @JvmSuppressWildcards (pageSize: Int) -> Flow<PagingData<Location>>

fun getLocationPaging(
    repository: LocationRepository,
    pageSize: Int,
) = repository.getLocationPagingStream(pageSize)
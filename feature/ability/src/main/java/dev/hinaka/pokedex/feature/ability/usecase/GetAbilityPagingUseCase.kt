package dev.hinaka.pokedex.feature.ability.usecase

import androidx.paging.PagingData
import dev.hinaka.pokedex.data.repository.AbilityRepository
import dev.hinaka.pokedex.domain.Ability
import kotlinx.coroutines.flow.Flow

typealias GetAbilityPagingUseCase =
    @JvmSuppressWildcards (pageSize: Int) -> Flow<PagingData<Ability>>

fun getAbilityPaging(
    repository: AbilityRepository,
    pageSize: Int,
) = repository.getAbilityPagingStream(pageSize)
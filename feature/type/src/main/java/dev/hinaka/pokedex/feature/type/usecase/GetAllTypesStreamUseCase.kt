package dev.hinaka.pokedex.feature.type.usecase

import dev.hinaka.pokedex.data.repository.TypeRepository
import dev.hinaka.pokedex.domain.type.Type
import kotlinx.coroutines.flow.Flow

typealias GetAllTypesStreamUseCase =
    @JvmSuppressWildcards () -> Flow<List<Type>>

fun getAllTypes(
    repository: TypeRepository
) = repository.getAllTypesStream()
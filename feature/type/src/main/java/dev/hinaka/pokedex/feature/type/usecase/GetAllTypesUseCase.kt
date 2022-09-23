package dev.hinaka.pokedex.feature.type.usecase

import dev.hinaka.pokedex.data.repository.TypeRepository
import dev.hinaka.pokedex.domain.type.Type

typealias GetAllTypesUseCase =
    @JvmSuppressWildcards suspend () -> List<Type>

suspend fun getAllTypes(
    repository: TypeRepository
) = repository.getAllTypes()
package dev.hinaka.pokedex.feature.initialload.usecase

import dev.hinaka.pokedex.data.repository.TypeRepository

typealias SyncTypesUseCase =
    @JvmSuppressWildcards suspend () -> Unit

suspend fun syncTypes(
    repository: TypeRepository
) = repository.getTypes()
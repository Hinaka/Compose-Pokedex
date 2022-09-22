package dev.hinaka.pokedex.feature.type.usecase

import dev.hinaka.pokedex.data.repository.TypeRepository
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type

typealias GetTypeDamageTakenRelationsUseCase =
    @JvmSuppressWildcards suspend (type: Type) -> Map<Type, DamageFactor>

suspend fun getTypeDamageTakenRelations(
    repository: TypeRepository,
    type: Type,
) = repository.getDamageTakenRelationsOf(type)
package dev.hinaka.pokedex.feature.type.usecase

import dev.hinaka.pokedex.data.repository.TypeRepository
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type

typealias GetDamageRelationsOfTypeUseCase =
    @JvmSuppressWildcards suspend (type: Type) -> Map<Type, DamageFactor>

suspend fun getDamageRelationsOfType(
    repository: TypeRepository,
    type: Type,
) = repository.getDamageRelationsOf(type)
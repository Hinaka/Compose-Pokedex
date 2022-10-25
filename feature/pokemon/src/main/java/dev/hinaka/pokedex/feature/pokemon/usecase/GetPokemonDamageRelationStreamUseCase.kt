package dev.hinaka.pokedex.feature.pokemon.usecase

import dev.hinaka.pokedex.data.repository.TypeRepository
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonDamageRelationStreamUseCase @Inject constructor(
    private val typeRepository: TypeRepository
) {
    suspend operator fun invoke(
        primaryType: Type,
        secondaryType: Type?
    ): Flow<Map<Type, DamageFactor>> {
        val primaryFlow = typeRepository.getDamageTakenRelationsStreamOf(primaryType)
        val secondaryFlow = secondaryType?.let {
            typeRepository.getDamageTakenRelationsStreamOf(it)
        } ?: flow { emit(emptyMap()) }

        return combine(primaryFlow, secondaryFlow) { primaryMap, secondaryMap ->
            primaryMap.toMutableMap().apply {
                secondaryMap.forEach {
                    merge(it.key, it.value, DamageFactor::times)
                }
            }
        }
    }
}
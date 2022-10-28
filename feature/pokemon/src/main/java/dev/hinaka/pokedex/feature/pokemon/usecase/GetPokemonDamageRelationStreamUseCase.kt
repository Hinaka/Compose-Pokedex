/*
 * Copyright 2022 Hinaka (Trung Nguyễn Minh Trần)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.hinaka.pokedex.feature.pokemon.usecase

import dev.hinaka.pokedex.data.repository.TypeRepository
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

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

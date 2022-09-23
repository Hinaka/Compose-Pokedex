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
package dev.hinaka.pokedex.feature.type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.feature.type.usecase.GetAllTypesStreamUseCase
import dev.hinaka.pokedex.feature.type.usecase.GetTypeDamageTakenRelationsStreamUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class TypeViewModel @Inject constructor(
    private val getAllTypesStreamUseCase: GetAllTypesStreamUseCase,
    private val getTypeDamageTakenRelationsStreamUseCase: GetTypeDamageTakenRelationsStreamUseCase
) : ViewModel() {

    private val selectedPrimaryType = MutableStateFlow<Type?>(null)
    private val selectedSecondaryType = MutableStateFlow<Type?>(null)

    private val primaryTypeDamageRelationMap = selectedPrimaryType.flatMapLatest {
        if (it == null) {
            flow { emit(emptyMap()) }
        } else {
            getTypeDamageTakenRelationsStreamUseCase(it)
        }
    }

    private val secondaryTypeDamageRelationMap = selectedSecondaryType.flatMapLatest {
        if (it == null) {
            flow { emit(emptyMap()) }
        } else {
            getTypeDamageTakenRelationsStreamUseCase(it)
        }
    }

    private val damageRelationMap: Flow<Map<Type, DamageFactor>> = combine(
        primaryTypeDamageRelationMap,
        secondaryTypeDamageRelationMap
    ) { primaryMap, secondaryMap ->
        primaryMap.toMutableMap().apply {
            secondaryMap.forEach {
                merge(it.key, it.value, DamageFactor::times)
            }
        }
    }

    val uiState: StateFlow<TypeScreenUiState> = combine(
        getAllTypesStreamUseCase(),
        selectedPrimaryType,
        selectedSecondaryType,
        damageRelationMap
    ) { allTypes, primaryType, secondaryType, damageRelationMap ->
        TypeScreenUiState(
            allTypes = allTypes,
            selectedPrimaryType = primaryType,
            selectedSecondaryType = secondaryType,
            damageRelation = damageRelationMap.toDamageRelation()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TypeScreenUiState()
    )

    fun selectPrimaryType(type: Type) {
        selectedPrimaryType.update { type }
    }

    fun selectSecondaryType(type: Type) {
        selectedSecondaryType.update { type }
    }
}

data class TypeScreenUiState(
    val allTypes: List<Type> = emptyList(),
    val selectedPrimaryType: Type? = null,
    val selectedSecondaryType: Type? = null,
    val damageRelation: DamageRelation = DamageRelation()
)

data class DamageRelation(
    val weakAgainstMap: Map<Type, DamageFactor> = emptyMap(),
    val resistantAgainstMap: Map<Type, DamageFactor> = emptyMap(),
    val normalAgainstMap: Map<Type, DamageFactor> = emptyMap()
) {
    val isEmpty
        get() = weakAgainstMap.isEmpty() &&
            resistantAgainstMap.isEmpty() &&
            normalAgainstMap.isEmpty()
}

private fun Map<Type, DamageFactor>.toDamageRelation(): DamageRelation {
    val (weakAgainst, notWeakAgainst) = toList().partition {
        it.second.isSuperEffective
    }
    val (resistantAgainst, rest) = notWeakAgainst.partition {
        it.second.isNotVeryEffective || it.second.isImmune
    }

    return DamageRelation(
        weakAgainstMap = weakAgainst.toMap(),
        resistantAgainstMap = resistantAgainst.toMap(),
        normalAgainstMap = rest.toMap()
    )
}

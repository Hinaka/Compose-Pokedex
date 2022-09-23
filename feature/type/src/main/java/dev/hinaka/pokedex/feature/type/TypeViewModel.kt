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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TypeViewModel @Inject constructor(
    private val getAllTypesStreamUseCase: GetAllTypesStreamUseCase,
    private val getTypeDamageTakenRelationsStreamUseCase: GetTypeDamageTakenRelationsStreamUseCase
) : ViewModel() {

    private val selectedTypeFlow = MutableStateFlow<Type?>(null)

    private val damageRelationMap = selectedTypeFlow.flatMapLatest {
        if (it == null) {
            flow { emit(emptyMap()) }
        } else {
            getTypeDamageTakenRelationsStreamUseCase(it)
        }
    }

    val uiState: StateFlow<TypeScreenUiState> = combine(
        getAllTypesStreamUseCase(),
        selectedTypeFlow,
        damageRelationMap,
    ) { allTypes, selectedType, damageRelationMap ->
        TypeScreenUiState(
            allTypes = allTypes,
            selectedType = selectedType,
            damageRelationMap = damageRelationMap,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TypeScreenUiState()
    )

    fun selectType(type: Type) {
        selectedTypeFlow.update { type }
    }
}

data class TypeScreenUiState(
    val allTypes: List<Type> = emptyList(),
    val selectedType: Type? = null,
    val damageRelationMap: Map<Type, DamageFactor> = emptyMap()
)

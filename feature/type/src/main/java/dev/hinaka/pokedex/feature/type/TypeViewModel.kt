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
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.domain.type.Type.Identifier.NORMAL
import dev.hinaka.pokedex.feature.type.usecase.GetAllTypesStreamUseCase
import dev.hinaka.pokedex.feature.type.usecase.GetTypeDamageTakenRelationsStreamUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TypeViewModel @Inject constructor(
    private val getAllTypesStreamUseCase: GetAllTypesStreamUseCase,
    private val getTypeDamageTakenRelationsStreamUseCase: GetTypeDamageTakenRelationsStreamUseCase
) : ViewModel() {

    val uiState: StateFlow<TypeScreenUiState> = combine(
        getAllTypesStreamUseCase(),
        getTypeDamageTakenRelationsStreamUseCase(
            Type(
                id = Id(7), identifier = NORMAL, name = "normal"
            )
        )
    ) { allTypes, damageRelationMap ->
        TypeScreenUiState(
            allTypes,
            damageRelationMap,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TypeScreenUiState()
    )
}

data class TypeScreenUiState(
    val allTypes: List<Type> = emptyList(),
    val damageRelationMap: Map<Type, DamageFactor> = emptyMap()
)

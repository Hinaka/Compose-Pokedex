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
package dev.hinaka.pokedex.feature.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Item
import dev.hinaka.pokedex.feature.item.usecase.GetItemPagingUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ItemViewModel @Inject constructor(
    getItemPagingUseCase: GetItemPagingUseCase
) : ViewModel() {

    private val itemPagingFlow = flow {
        emit(getItemPagingUseCase(10).cachedIn(viewModelScope))
    }

    val uiState: StateFlow<ItemScreenUiState> = itemPagingFlow.map {
        ItemScreenUiState(
            itemPagingFlow = it
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ItemScreenUiState()
    )
}

data class ItemScreenUiState(
    val itemPagingFlow: Flow<PagingData<Item>> = emptyFlow()
)

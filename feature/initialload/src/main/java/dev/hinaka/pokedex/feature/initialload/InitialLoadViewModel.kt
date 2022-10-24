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
package dev.hinaka.pokedex.feature.initialload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.feature.initialload.usecase.SyncEggGroupsUseCase
import dev.hinaka.pokedex.feature.initialload.usecase.SyncTypesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitialLoadViewModel @Inject constructor(
    private val syncTypesUseCase: SyncTypesUseCase,
    private val syncEggGroupsUseCase: SyncEggGroupsUseCase,
) : ViewModel() {
    private var _isReady = false
    val isReady get() = _isReady

    init {
        viewModelScope.launch {
            syncTypesUseCase()
            syncEggGroupsUseCase()
            _isReady = true
        }
    }
}

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

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.hinaka.pokedex.domain.type.Type

@Composable
fun TypeRoute(
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    typeViewModel: TypeViewModel = hiltViewModel()
) {
    val uiState by typeViewModel.uiState.collectAsState()

    TypeRoute(
        uiState = uiState,
        openDrawer = openDrawer,
        onPrimaryTypeSelected = typeViewModel::selectPrimaryType,
        onSecondaryTypeSelected = typeViewModel::selectSecondaryType,
        modifier = modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TypeRoute(
    uiState: TypeScreenUiState,
    openDrawer: () -> Unit,
    onPrimaryTypeSelected: (Type?) -> Unit,
    onSecondaryTypeSelected: (Type?) -> Unit,
    modifier: Modifier = Modifier
) {
    val appBarState = rememberTopAppBarState()

    TypeScreen(
        uiState = uiState,
        appBarState = appBarState,
        openDrawer = openDrawer,
        onPrimaryTypeSelected = onPrimaryTypeSelected,
        onSecondaryTypeSelected = onSecondaryTypeSelected,
        modifier
    )
}


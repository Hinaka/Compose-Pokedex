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
package dev.hinaka.pokedex.feature.nature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.hinaka.pokedex.domain.Nature
import kotlinx.coroutines.flow.Flow

@Composable
fun NatureRoute(
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    natureViewModel: NatureViewModel = hiltViewModel()
) {
    val uiState by natureViewModel.uiState.collectAsState()

    NatureRoute(uiState = uiState, openDrawer = openDrawer, modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NatureRoute(
    uiState: NatureScreenUiState,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyPagingItems = uiState.naturePagingFlow.collectAsLazyPagingItems()
    val lazyListState = rememberLazyListState()
    val appBarState = rememberTopAppBarState()

    NatureListScreen(
        lazyPagingItems = lazyPagingItems,
        lazyListState = lazyListState,
        appBarState = appBarState,
        openDrawer = openDrawer,
        modifier = modifier,
    )
}
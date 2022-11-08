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

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import dev.hinaka.pokedex.feature.item.usecase.ItemListScreen

@Composable
fun ItemRoute(
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    itemViewModel: ItemViewModel = hiltViewModel()
) {
    val uiState by itemViewModel.uiState.collectAsState()

    ItemRoute(uiState = uiState, openDrawer = openDrawer, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ItemRoute(
    uiState: ItemScreenUiState,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyPagingItems = uiState.itemPagingFlow.collectAsLazyPagingItems()
    val lazyListState = rememberLazyListState()
    val appBarState = rememberTopAppBarState()

    ItemListScreen(
        lazyPagingItems = lazyPagingItems,
        lazyListState = lazyListState,
        appBarState = appBarState,
        openDrawer = openDrawer,
        modifier = modifier,
    )
}


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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
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
    modifier: Modifier = Modifier,
    natureViewModel: NatureViewModel = hiltViewModel(),
) {
    val uiState by natureViewModel.uiState.collectAsState()

    NatureScreen(
        naturePagingFlow = uiState.naturePagingFlow,
        modifier = modifier
    )
}

@Composable
fun NatureScreen(
    naturePagingFlow: Flow<PagingData<Nature>>,
    modifier: Modifier = Modifier,
) {
    val lazyPagingItems = naturePagingFlow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(lazyPagingItems, { it.id.value }) { nature ->
            nature?.let {
                Nature(nature = it, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun Nature(
    nature: Nature,
    modifier: Modifier = Modifier,
) {
    Text(text = nature.name)
}

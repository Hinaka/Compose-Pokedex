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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Item
import dev.hinaka.pokedex.feature.item.R.drawable
import kotlinx.coroutines.flow.Flow

@Composable
fun ItemRoute(
    modifier: Modifier = Modifier,
    itemViewModel: ItemViewModel = hiltViewModel(),
) {
    val uiState by itemViewModel.uiState.collectAsState()

    ItemScreen(
        itemPagingFlow = uiState.itemPagingFlow,
    )
}

@Composable
fun ItemScreen(
    itemPagingFlow: Flow<PagingData<Item>>,
    modifier: Modifier = Modifier,
) {
    val lazyPagingItems = itemPagingFlow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(lazyPagingItems, { it.id.value }) { item ->
            item?.let {
                Item(item = it, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun Item(
    item: Item,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(Min)
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = item.effect, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.width(8.dp))
            AsyncImage(
                model = item.imageUrl,
                contentDescription = "Image of item ${item.name}",
                placeholder = painterResource(id = drawable.ic_pokeball),
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = 80.dp,
                        minHeight = 80.dp
                    )
                    .align(CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun ItemPreview() {
    PokedexTheme {
        Item(
            item = Item(
                id = Id(1),
                name = "Master Ball",
                effect = "Catches a wild Pokémon every time.",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/master-ball.png"
            ),
            modifier = Modifier.width(480.dp)
        )
    }
}
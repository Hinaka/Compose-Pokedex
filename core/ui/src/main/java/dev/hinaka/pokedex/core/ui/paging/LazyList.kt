package dev.hinaka.pokedex.core.ui.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items

fun LazyListScope.appendStateItem(loadState: LoadState) {
    when (loadState) {
        is NotLoading -> Unit
        Loading -> {
            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        is Error -> Unit
    }
}

fun LazyListScope.refreshStateItem(loadState: LoadState) {
    when (loadState) {
        is NotLoading -> Unit
        Loading -> {
            item {
                Row(
                    Modifier.fillParentMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        is Error -> TODO()
    }
}

fun <T : Any> LazyListScope.itemsWithLoadState(
    lazyPagingItems: LazyPagingItems<T>,
    key: ((item: T) -> Any)? = null,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
    items(
        items = lazyPagingItems,
        key = key,
        itemContent = itemContent
    )

    appendStateItem(lazyPagingItems.loadState.append)
    refreshStateItem(lazyPagingItems.loadState.refresh)
}
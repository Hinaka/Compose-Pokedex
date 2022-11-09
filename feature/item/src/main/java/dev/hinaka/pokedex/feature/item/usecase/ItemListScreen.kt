package dev.hinaka.pokedex.feature.item.usecase

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import dev.hinaka.pokedex.core.designsystem.component.PkdxAppBar
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.core.ui.paging.itemsWithLoadState
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Item
import dev.hinaka.pokedex.feature.item.R.drawable

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ItemListScreen(
    lazyPagingItems: LazyPagingItems<Item>,
    lazyListState: LazyListState,
    appBarState: TopAppBarState,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PkdxAppBar(
                title = "Item Dex",
                openDrawer = openDrawer,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        ItemList(
            lazyPagingItems = lazyPagingItems,
            modifier = Modifier.consumedWindowInsets(innerPadding),
            state = lazyListState,
            contentPadding = innerPadding
        )
    }
}

@Composable
private fun ItemList(
    lazyPagingItems: LazyPagingItems<Item>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsWithLoadState(lazyPagingItems, { it.id.value }) { item ->
            item?.let {
                Item(item = it, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun Item(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseSurface
        )
    ) {
        Row(
            modifier = Modifier
                .height(Min)
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
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
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ItemPreview() {
    PokedexTheme {
        Item(
            item = Item(
                id = Id(1),
                name = "Master Ball",
                effect = "Catches a wild Pokémon every time.",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/" +
                    "sprites/items/master-ball.png"
            ),
            modifier = Modifier.width(480.dp)
        )
    }
}

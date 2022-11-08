package dev.hinaka.pokedex.feature.location

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import dev.hinaka.pokedex.core.designsystem.component.PkdxAppBar
import dev.hinaka.pokedex.domain.Location
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LocationListScreen(
    lazyPagingItems: LazyPagingItems<Location>,
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
                title = "Location Dex",
                openDrawer = openDrawer,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        LocationList(
            lazyPagingItems = lazyPagingItems,
            modifier = Modifier.consumedWindowInsets(innerPadding),
            state = lazyListState,
            contentPadding = innerPadding
        )
    }
}

@Composable
private fun LocationList(
    lazyPagingItems: LazyPagingItems<Location>,
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
        items(lazyPagingItems, { it.id.value }) { location ->
            location?.let {
                Location(location = it, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun Location(
    location: Location,
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
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.titleMedium.toSpanStyle()) {
                        append(location.name)
                    }
                    withStyle(style = MaterialTheme.typography.titleSmall.toSpanStyle()) {
                        append(" in ")
                        append(
                            location.region.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            }
                        )
                    }
                }
            )
        }
    }
}

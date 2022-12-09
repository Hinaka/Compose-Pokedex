package dev.hinaka.pokedex.feature.move

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import dev.hinaka.pokedex.core.designsystem.component.PkdxAppBar
import dev.hinaka.pokedex.core.ui.paging.itemsWithLoadState
import dev.hinaka.pokedex.core.ui.type.PokemonType
import dev.hinaka.pokedex.domain.move.DamageClass
import dev.hinaka.pokedex.domain.move.DamageClass.PHYSICAL
import dev.hinaka.pokedex.domain.move.DamageClass.SPECIAL
import dev.hinaka.pokedex.domain.move.DamageClass.STATUS
import dev.hinaka.pokedex.domain.move.Move
import dev.hinaka.pokedex.feature.move.R.string

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
internal fun MoveListScreen(
    lazyPagingItems: LazyPagingItems<Move>,
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
                title = stringResource(string.move_app_bar_title),
                openDrawer = openDrawer,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        MoveList(
            lazyPagingItems = lazyPagingItems,
            modifier = Modifier.consumedWindowInsets(innerPadding),
            state = lazyListState,
            contentPadding = innerPadding,
        )
    }
}

@Composable
private fun MoveList(
    lazyPagingItems: LazyPagingItems<Move>,
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
                Move(move = it, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun Move(
    move: Move,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseSurface
        )
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = move.name,
                modifier = Modifier.weight(4f),
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = move.power.toString(),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = move.acc.toString(),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = move.pp.toString(),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.labelMedium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(Min)
        ) {
            PokemonType(
                type = move.type,
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(8.dp))
            DamageClass(
                damageClass = move.damageClass,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun DamageClass(
    damageClass: DamageClass,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = CircleShape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = damageClass.displayName,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

private val DamageClass.displayName
    @Composable get() = when (this) {
        STATUS -> "STATUS"
        PHYSICAL -> "PHYSICAL"
        SPECIAL -> "SPECIAL"
    }
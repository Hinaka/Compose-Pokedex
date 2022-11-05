package dev.hinaka.pokedex.feature.pokemon.ui.details.section

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Stats
import dev.hinaka.pokedex.feature.pokemon.R.string
import dev.hinaka.pokedex.feature.pokemon.ui.details.LocalDetailsTheme
import dev.hinaka.pokedex.feature.pokemon.ui.details.section.Tabs.BASE
import dev.hinaka.pokedex.feature.pokemon.ui.details.section.Tabs.MAX
import dev.hinaka.pokedex.feature.pokemon.ui.details.section.Tabs.MIN

private enum class Tabs(@StringRes private val labelId: Int) {
    BASE(string.pokemon_details_stats_base),
    MIN(string.pokemon_details_stats_min),
    MAX(string.pokemon_details_stats_max);

    val label @Composable get() = stringResource(id = labelId)
}

@Composable
internal fun BaseStatsSection(
    baseStats: Stats,
    minStats: Stats,
    maxStats: Stats,
    modifier: Modifier = Modifier
) {
    Section(title = stringResource(string.pokemon_details_stats_section), modifier) {
        var selectedIndex by remember { mutableStateOf(0) }

        StatsTabRow(
            selectedIndex = selectedIndex,
            onChange = { selectedIndex = it },
            Modifier.defaultMinSize(minHeight = 48.dp)
        )
        Space(dp = 8.dp)
        when (Tabs.values()[selectedIndex]) {
            BASE -> BaseStatsContent(baseStats, Modifier.fillMaxWidth())
            MIN -> MinStatsContent(minStats, Modifier.fillMaxWidth())
            MAX -> MaxStatsContent(maxStats, Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun StatsTabRow(
    selectedIndex: Int,
    onChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier,
        indicator = {},
        divider = {}
    ) {
        Tabs.values().forEachIndexed { index, tab ->
            StatsTab(
                label = tab.label,
                selected = index == selectedIndex,
                onClick = { onChange(index) }
            )
        }
    }
}

@Composable
private fun StatsTab(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Tab(selected = selected, onClick = onClick,
        selectedContentColor = LocalDetailsTheme.current.onPrimaryColor,
        unselectedContentColor = LocalDetailsTheme.current.onPrimaryColor.copy(alpha = 0.4f),
        text = {
            Text(text = label, style = MaterialTheme.typography.titleSmall)
        })
}

@Composable
private fun BaseStatsContent(
    stats: Stats,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        StatRows(stats = stats, Modifier.fillMaxWidth())
        Space(dp = 8.dp)
        Text(
            text = buildAnnotatedString {
                append(stringResource(string.pokemon_details_stats_total))
                withStyle(SpanStyle(color = LocalDetailsTheme.current.onPrimaryColor)) {
                    append(stats.total.toString())
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun MinStatsContent(
    stats: Stats,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        StatRows(stats = stats, Modifier.fillMaxWidth())
        Space(dp = 8.dp)
        Text(
            text = stringResource(string.pokemon_details_stats_min_explain),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MaxStatsContent(
    stats: Stats,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        StatRows(stats = stats, Modifier.fillMaxWidth())
        Space(dp = 8.dp)
        Text(
            text = stringResource(string.pokemon_details_stats_max_explain),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun StatRows(
    stats: Stats,
    modifier: Modifier = Modifier
) {
    val max = stats.max.toFloat()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatRow(
            name = stringResource(string.pokemon_details_stats_hp),
            value = stats.hp,
            ratioToMax = stats.hp / max,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            name = stringResource(string.pokemon_details_stats_attack),
            value = stats.attack,
            ratioToMax = stats.attack / max,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            name = stringResource(string.pokemon_details_stats_defense),
            value = stats.defense,
            ratioToMax = stats.defense / max,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            name = stringResource(string.pokemon_details_stats_sp_attack),
            value = stats.specialAttack,
            ratioToMax = stats.specialAttack / max,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            name = stringResource(string.pokemon_details_stats_sp_defense),
            value = stats.specialDefense,
            ratioToMax = stats.specialDefense / max,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            name = stringResource(string.pokemon_details_stats_speed),
            value = stats.speed,
            ratioToMax = stats.speed / max,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun StatRow(
    name: String,
    value: Int,
    ratioToMax: Float,
    modifier: Modifier = Modifier
) {
    val containerColor = LocalDetailsTheme.current.primaryColor
    val contentColor = LocalDetailsTheme.current.onPrimaryColor

    val baseShape = MaterialTheme.shapes.small
    val nameShape = baseShape.copy(
        topEnd = ZeroCornerSize,
        bottomEnd = ZeroCornerSize
    )
    val valueShape = baseShape.copy(
        topStart = ZeroCornerSize,
        bottomStart = ZeroCornerSize
    )
    val paddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)

    Row(modifier) {
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .background(
                    color = containerColor,
                    shape = nameShape
                )
                .padding(paddingValues),
            color = contentColor,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value.toString(),
            modifier = Modifier
                .fillMaxWidth(ratioToMax)
                .background(
                    color = containerColor.copy(alpha = 0.6f),
                    shape = valueShape
                )
                .padding(paddingValues),
            color = contentColor,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
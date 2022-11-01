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
package dev.hinaka.pokedex.feature.pokemon.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
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
import dev.hinaka.pokedex.core.designsystem.component.PkdxCard
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Stats
import dev.hinaka.pokedex.domain.pokemon.maxStats
import dev.hinaka.pokedex.domain.pokemon.minStats
import dev.hinaka.pokedex.feature.pokemon.R.string
import dev.hinaka.pokedex.feature.pokemon.ui.details.BaseStatsTab.BASE
import dev.hinaka.pokedex.feature.pokemon.ui.details.BaseStatsTab.MAX
import dev.hinaka.pokedex.feature.pokemon.ui.details.BaseStatsTab.MIN

@Composable
internal fun InfoSections(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        SpeciesSection(species = pokemon.species, Modifier.fillMaxWidth())
        Space(dp = 16.dp)
        AbilitiesSection(abilities = pokemon.abilities, Modifier.fillMaxWidth())
        Space(dp = 16.dp)
        baseStatsSection(
            baseStats = pokemon.baseStats,
            minStats = pokemon.minStats,
            maxStats = pokemon.maxStats,
        )
    }
}

@Composable
private fun ColumnScope.baseStatsSection(
    baseStats: Stats,
    minStats: Stats,
    maxStats: Stats,
) {
    SectionTitle(title = "Base Stats")
    Space(dp = 8.dp)
    PkdxCard {
        var selectedIndex by remember { mutableStateOf(0) }

        TabRow(
            selectedTabIndex = selectedIndex,
            modifier = Modifier.defaultMinSize(minHeight = 48.dp),
            indicator = {},
            divider = { Space(dp = 8.dp) }
        ) {
            BaseStatsTab.values().forEachIndexed { index, tab ->
                Tab(
                    selected = index == selectedIndex,
                    onClick = { selectedIndex = index },
                    selectedContentColor = LocalDetailsTheme.current.onPrimaryColor,
                    unselectedContentColor = LocalDetailsTheme.current.onPrimaryColor
                        .copy(alpha = 0.4f)
                ) {
                    Text(text = tab.label, style = MaterialTheme.typography.titleSmall)
                }
            }
        }
        Space(dp = 8.dp)
        when (BaseStatsTab.values()[selectedIndex]) {
            BASE -> {
                StatRows(
                    stats = baseStats,
                    modifier = Modifier.fillMaxWidth()
                )
                Space(dp = 8.dp)
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(string.pokemon_details_stats_total))
                        withStyle(SpanStyle(color = LocalDetailsTheme.current.primaryColor)) {
                            append(baseStats.total.toString())
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            MIN -> {
                StatRows(
                    stats = minStats,
                    modifier = Modifier.fillMaxWidth()
                )
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

            MAX -> {
                StatRows(
                    stats = maxStats,
                    modifier = Modifier.fillMaxWidth()
                )
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
    }
}

private enum class BaseStatsTab(
    val labelId: Int
) {
    BASE(string.pokemon_details_stats_tab_base),
    MIN(string.pokemon_details_stats_tab_min),
    MAX(string.pokemon_details_stats_tab_max);

    val label @Composable get() = stringResource(id = labelId)
}

@Composable
private fun StatRows(
    stats: Stats,
    modifier: Modifier = Modifier
) {
    val maxStat = stats.max.toFloat()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatRow(
            label = stringResource(string.pokemon_details_stats_hp),
            value = stats.hp,
            valueRatio = stats.hp / maxStat,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            label = stringResource(string.pokemon_details_stats_attack),
            value = stats.attack,
            valueRatio = stats.attack / maxStat,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            label = stringResource(string.pokemon_details_stats_defense),
            value = stats.defense,
            valueRatio = stats.defense / maxStat,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            label = stringResource(string.pokemon_details_stats_sp_attack),
            value = stats.specialAttack,
            valueRatio = stats.specialAttack / maxStat,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            label = stringResource(string.pokemon_details_stats_sp_defense),
            value = stats.specialDefense,
            valueRatio = stats.specialDefense / maxStat,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            label = stringResource(string.pokemon_details_stats_speed),
            value = stats.speed,
            valueRatio = stats.speed / maxStat,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun StatRow(
    label: String,
    value: Int,
    valueRatio: Float,
    modifier: Modifier
) {
    val containerColor = LocalDetailsTheme.current.primaryColor
    val contentColor = LocalDetailsTheme.current.onPrimaryColor

    Row(modifier = modifier) {
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .background(
                    color = containerColor,
                    shape = MaterialTheme.shapes.small.copy(
                        topEnd = CornerSize(0),
                        bottomEnd = CornerSize(0)
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = contentColor,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value.toString(),
            modifier = Modifier
                .fillMaxWidth(valueRatio)
                .background(
                    color = containerColor.copy(alpha = 0.6f),
                    shape = MaterialTheme.shapes.small.copy(
                        topStart = CornerSize(0),
                        bottomStart = CornerSize(0)
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = contentColor,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ColumnScope.SectionTitle(
    title: String
) {
    Text(
        text = title,
        modifier = Modifier.align(Alignment.CenterHorizontally),
        style = MaterialTheme.typography.titleMedium
    )
}

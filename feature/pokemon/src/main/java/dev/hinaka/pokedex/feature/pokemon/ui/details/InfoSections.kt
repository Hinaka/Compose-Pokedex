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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.PkdxCard
import dev.hinaka.pokedex.core.designsystem.component.PokedexIcon
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.core.ui.type.onTypeContainerColor
import dev.hinaka.pokedex.core.ui.type.typeContainerColor
import dev.hinaka.pokedex.core.ui.utils.preview.PokemonPreviewParameterProvider
import dev.hinaka.pokedex.domain.Ability
import dev.hinaka.pokedex.domain.EmptyAbility
import dev.hinaka.pokedex.domain.pokemon.Height
import dev.hinaka.pokedex.domain.pokemon.PokemonDeprecated
import dev.hinaka.pokedex.domain.pokemon.Stats
import dev.hinaka.pokedex.domain.pokemon.Weight
import dev.hinaka.pokedex.domain.pokemon.max
import dev.hinaka.pokedex.domain.pokemon.maxStats
import dev.hinaka.pokedex.domain.pokemon.minStats
import dev.hinaka.pokedex.domain.pokemon.total
import dev.hinaka.pokedex.feature.pokemon.R.string
import dev.hinaka.pokedex.feature.pokemon.ui.details.BaseStatsTab.BASE
import dev.hinaka.pokedex.feature.pokemon.ui.details.BaseStatsTab.MAX
import dev.hinaka.pokedex.feature.pokemon.ui.details.BaseStatsTab.MIN

@Composable
internal fun InfoSections(
    pokemonDeprecated: PokemonDeprecated,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        speciesSection(
            flavorText = pokemonDeprecated.flavorText,
            height = pokemonDeprecated.height,
            weight = pokemonDeprecated.weight
        )

        Space(dp = 16.dp)
        abilitiesSection(
            normalAbilities = pokemonDeprecated.normalAbilities,
            hiddenAbility = pokemonDeprecated.hiddenAbility,
            containerColor = containerColor,
            contentColor = contentColor
        )

        Space(dp = 16.dp)
        baseStatsSection(
            baseStats = pokemonDeprecated.baseStats,
            minStats = pokemonDeprecated.minStats,
            maxStats = pokemonDeprecated.maxStats,
            containerColor = containerColor,
            contentColor = contentColor
        )
    }
}

@Composable
private fun ColumnScope.speciesSection(
    flavorText: String,
    height: Height,
    weight: Weight
) {
    SectionTitle(title = "Species")
    Space(dp = 8.dp)
    PkdxCard {
        OutlinedText(text = flavorText, modifier = Modifier.fillMaxWidth())
        Space(dp = 4.dp)
        Text(
            text = "Pokedex entry",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Space(dp = 8.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                OutlinedText(
                    text = "%.2f".format(height.meter) + " m",
                    modifier = Modifier.fillMaxWidth()
                )
                Space(dp = 4.dp)
                Text(
                    text = "Height",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                OutlinedText(
                    text = "%.2f".format(weight.kg) + " kg",
                    modifier = Modifier.fillMaxWidth()
                )
                Space(dp = 4.dp)
                Text(
                    text = "Weight",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.abilitiesSection(
    normalAbilities: List<Ability>,
    hiddenAbility: Ability,
    containerColor: Color,
    contentColor: Color
) {
    SectionTitle(title = "Abilities")
    Space(dp = 8.dp)
    PkdxCard {
        if (normalAbilities.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                normalAbilities.forEach {
                    AbilityItem(
                        ability = it,
                        containerColor = containerColor,
                        contentColor = contentColor
                    )
                }
            }
            Space(dp = 8.dp)
        }

        if (hiddenAbility != EmptyAbility) {
            HiddenAbilityItem(
                ability = hiddenAbility,
                containerColor = containerColor,
                contentColor = contentColor
            )
        }
    }
}

@Composable
private fun ColumnScope.baseStatsSection(
    baseStats: Stats,
    minStats: Stats,
    maxStats: Stats,
    containerColor: Color,
    contentColor: Color
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
                    selectedContentColor = contentColor,
                    unselectedContentColor = contentColor.copy(alpha = 0.4f)
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
                    containerColor = containerColor,
                    contentColor = contentColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Space(dp = 8.dp)
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(string.pokemon_details_stats_total))
                        withStyle(SpanStyle(color = containerColor)) {
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
                    containerColor = containerColor,
                    contentColor = contentColor,
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
                    containerColor = containerColor,
                    contentColor = contentColor,
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
    containerColor: Color,
    contentColor: Color,
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
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            label = stringResource(string.pokemon_details_stats_attack),
            value = stats.attack,
            valueRatio = stats.attack / maxStat,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            label = stringResource(string.pokemon_details_stats_defense),
            value = stats.defense,
            valueRatio = stats.defense / maxStat,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            label = stringResource(string.pokemon_details_stats_sp_attack),
            value = stats.specialAttack,
            valueRatio = stats.specialAttack / maxStat,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            label = stringResource(string.pokemon_details_stats_sp_defense),
            value = stats.specialDefense,
            valueRatio = stats.specialDefense / maxStat,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = Modifier.fillMaxWidth()
        )
        StatRow(
            label = stringResource(string.pokemon_details_stats_speed),
            value = stats.speed,
            valueRatio = stats.speed / maxStat,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun OutlinedText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        modifier = modifier
            .border(
                width = Dp.Hairline,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 8.dp, horizontal = 8.dp)
    )
}

@Composable
private fun StatRow(
    label: String,
    value: Int,
    valueRatio: Float,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier
) {
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

@Composable
private fun AbilityItem(
    ability: Ability,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .background(
                color = containerColor,
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Companion.CenterVertically
    ) {
        Text(
            text = ability.name,
            color = contentColor,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        Space(dp = 8.dp)
        PokedexIcon(
            icon = PokedexIcons.Info,
            contentDescription = "",
            tint = contentColor
        )
    }
}

@Composable
private fun HiddenAbilityItem(
    ability: Ability,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .background(
                color = containerColor,
                shape = MaterialTheme.shapes.small
            ),
        verticalAlignment = Companion.CenterVertically
    ) {
        Text(
            text = "Hidden",
            color = contentColor,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        Row(
            modifier = Modifier
                .weight(1f)
                .background(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f),
                    shape = MaterialTheme.shapes.small.copy(
                        topStart = CornerSize(0),
                        bottomStart = CornerSize(0)
                    )
                )
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Companion.CenterVertically
        ) {
            Text(
                text = ability.name,
                color = contentColor,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
            Space(dp = 8.dp)
            PokedexIcon(
                icon = PokedexIcons.Info,
                contentDescription = "",
                tint = contentColor
            )
        }
    }
}

@Preview
@Composable
private fun InfoSectionPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class, limit = 1) pokemonDeprecated: PokemonDeprecated
) {
    PokedexTheme {
        val containerColor = pokemonDeprecated.types.first().typeContainerColor
        val contentColor = pokemonDeprecated.types.first().onTypeContainerColor
        InfoSections(
            pokemonDeprecated = pokemonDeprecated,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = Modifier.size(
                width = 360.dp,
                height = 480.dp
            )
        )
    }
}

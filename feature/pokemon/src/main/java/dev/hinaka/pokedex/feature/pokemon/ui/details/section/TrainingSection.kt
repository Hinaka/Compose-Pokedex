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
package dev.hinaka.pokedex.feature.pokemon.ui.details.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.OutlinedText
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.component.SubLabel
import dev.hinaka.pokedex.core.ui.utils.decimalFormat
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Stats
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Training
import dev.hinaka.pokedex.feature.pokemon.R.string
import dev.hinaka.pokedex.feature.pokemon.ui.details.LocalDetailsTheme

@Composable
internal fun TrainingSection(
    training: Training,
    modifier: Modifier = Modifier
) {
    Section(title = stringResource(string.pokemon_details_training_section), modifier) {
        OutlinedText(
            text = buildEffortString(effort = training.effortYield),
            Modifier.fillMaxWidth()
        )
        SubLabel(
            text = stringResource(string.pokemon_details_training_ev_yield),
            Modifier.align(CenterHorizontally)
        )

        Space(dp = 8.dp)
        OutlinedText(
            text = buildCatchRateString(
                catchRate = training.catchRate,
                catchRatePercentAtFullHp = training.catchRatePercentAtFullHp
            ),
            Modifier.fillMaxWidth()
        )
        SubLabel(
            text = stringResource(string.pokemon_details_training_catch_rate),
            Modifier.align(CenterHorizontally)
        )

        Space(dp = 8.dp)
        OutlinedText(
            text = stringResource(
                string.pokemon_details_training_growth_rate_format,
                training.growthRate.name.uppercase(),
                training.growthRate.expToMaxLevel.decimalFormat()
            ),
            Modifier.fillMaxWidth()
        )
        SubLabel(
            text = stringResource(string.pokemon_details_training_growth_rate),
            Modifier.align(CenterHorizontally)
        )

        Space(dp = 8.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                OutlinedText(
                    text = training.baseHappiness.toString(),
                    color = LocalDetailsTheme.current.onPrimaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Space(dp = 4.dp)
                SubLabel(
                    text = stringResource(string.pokemon_details_training_base_happiness),
                    modifier = Modifier.align(CenterHorizontally)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                OutlinedText(
                    text = training.baseExp.toString(),
                    color = LocalDetailsTheme.current.onPrimaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Space(dp = 4.dp)
                SubLabel(
                    text = stringResource(string.pokemon_details_training_base_experience),
                    modifier = Modifier.align(CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun buildEffortString(effort: Stats) = buildAnnotatedString {
    val highlightColor = LocalDetailsTheme.current.onPrimaryColor
    val stats = mutableListOf<Pair<String, Int>>()
    val addStatIfNotZero: (label: String, value: Int) -> Unit = { label, value ->
        if (value > 0) {
            stats.add(label to value)
        }
    }

    addStatIfNotZero(stringResource(string.pokemon_details_stats_hp), effort.hp)
    addStatIfNotZero(stringResource(string.pokemon_details_stats_attack), effort.attack)
    addStatIfNotZero(stringResource(string.pokemon_details_stats_defense), effort.defense)
    addStatIfNotZero(stringResource(string.pokemon_details_stats_sp_attack), effort.specialAttack)
    addStatIfNotZero(stringResource(string.pokemon_details_stats_sp_defense), effort.specialDefense)
    addStatIfNotZero(stringResource(string.pokemon_details_stats_speed), effort.speed)

    stats.forEachIndexed { index, (label, value) ->
        withStyle(SpanStyle(color = highlightColor)) {
            append(value.toString())
        }
        append(" $label")
        if (index != stats.lastIndex) append(" - ")
    }
}

@Composable
private fun buildCatchRateString(
    catchRate: Int,
    catchRatePercentAtFullHp: Float
) = buildAnnotatedString {
    withStyle(SpanStyle(color = LocalDetailsTheme.current.onPrimaryColor)) {
        append(catchRate.toString())
    }
    append(" ")
    append(
        stringResource(
            string.pokemon_details_training_catch_rate_full_hp_format,
            catchRatePercentAtFullHp.decimalFormat()
        )
    )
}

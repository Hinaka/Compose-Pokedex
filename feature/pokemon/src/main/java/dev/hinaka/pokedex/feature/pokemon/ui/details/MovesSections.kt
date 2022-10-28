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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.PkdxCard
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.domain.move.LearnMethod
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.feature.pokemon.R.string
import dev.hinaka.pokedex.feature.pokemon.ui.details.MoveLearnMethod.EGG
import dev.hinaka.pokedex.feature.pokemon.ui.details.MoveLearnMethod.LEVEL
import dev.hinaka.pokedex.feature.pokemon.ui.details.MoveLearnMethod.TM
import dev.hinaka.pokedex.feature.pokemon.ui.details.MoveLearnMethod.TUTOR

@Composable
fun MovesSections(
    pokemon: Pokemon,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        var selectedIndex by remember { mutableStateOf(0) }

        PkdxCard {
            TabRow(
                selectedTabIndex = selectedIndex,
                modifier = Modifier.defaultMinSize(minHeight = 48.dp),
                indicator = {},
                divider = { Space(dp = 8.dp) }
            ) {
                MoveLearnMethod.values().forEachIndexed { index, tab ->
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
            Space(dp = 4.dp)
            Text(
                text = "Move learn methods",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Space(dp = 16.dp)
        PkdxCard {
            val moves = when (MoveLearnMethod.values()[selectedIndex]) {
                LEVEL -> pokemon.learnableMoves.filter { it.learnMethod == LearnMethod.LEVEL }
                TM -> pokemon.learnableMoves.filter { it.learnMethod == LearnMethod.TM }
                EGG -> pokemon.learnableMoves.filter { it.learnMethod == LearnMethod.EGG }
                TUTOR -> pokemon.learnableMoves.filter { it.learnMethod == LearnMethod.TUTOR }
            }

            LazyColumn {
                items(moves) { learnableMove ->
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val move = learnableMove.move
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
                        Space(dp = 8.dp)
                    }
                }
            }
        }
    }
}

enum class MoveLearnMethod(
    private val labelId: Int
) {
    LEVEL(string.pokemon_details_moves_tab_level),
    TM(string.pokemon_details_moves_tab_tm),
    EGG(string.pokemon_details_moves_tab_egg),
    TUTOR(string.pokemon_details_moves_tab_tutor);

    val label @Composable get() = stringResource(id = labelId)
}

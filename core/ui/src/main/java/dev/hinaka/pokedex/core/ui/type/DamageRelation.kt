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
package dev.hinaka.pokedex.core.ui.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type

@Composable
fun DamageRelationChart(
    damageRelationMap: Map<Type, DamageFactor>,
    modifier: Modifier = Modifier
) {
    val (weakAgainst, rest) = remember(damageRelationMap) {
        damageRelationMap.toList().partition {
            it.second.isSuperEffective
        }
    }
    val (resistantAgainst, normalAgainst) = remember(rest) {
        rest.partition {
            it.second.isNotVeryEffective || it.second.isImmune
        }
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        DamageRelationSection(
            label = "Weak against...",
            damageRelations = weakAgainst,
            modifier = Modifier.fillMaxWidth()
        )
        DamageRelationSection(
            label = "Resistant against...",
            damageRelations = resistantAgainst,
            modifier = Modifier.fillMaxWidth()
        )
        DamageRelationSection(
            label = "Normal damage from...",
            damageRelations = normalAgainst,
            showDamageFactor = false,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun DamageRelationSection(
    label: String,
    damageRelations: List<Pair<Type, DamageFactor>>,
    modifier: Modifier = Modifier,
    showDamageFactor: Boolean = true
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = label,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        damageRelations.chunked(3).forEach { chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                chunk.forEach { (type, damageFactor) ->
                    PokemonTypeWithDamageFactor(
                        type = type,
                        damageFactor = damageFactor,
                        modifier = Modifier.weight(1f),
                        showDamageFactor = showDamageFactor
                    )
                }
            }
        }
    }
}

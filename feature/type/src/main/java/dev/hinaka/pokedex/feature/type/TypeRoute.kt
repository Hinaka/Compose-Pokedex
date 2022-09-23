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
package dev.hinaka.pokedex.feature.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.hinaka.pokedex.core.ui.PokemonType
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type

@Composable
fun TypeRoute(
    modifier: Modifier = Modifier,
    typeViewModel: TypeViewModel = hiltViewModel()
) {
    val uiState by typeViewModel.uiState.collectAsState()

    TypeScreen(
        uiState = uiState,
        onPrimaryTypeSelected = typeViewModel::selectPrimaryType,
        onSecondaryTypeSelected = typeViewModel::selectSecondaryType,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun TypeScreen(
    uiState: TypeScreenUiState,
    onPrimaryTypeSelected: (Type?) -> Unit,
    onSecondaryTypeSelected: (Type?) -> Unit,
    modifier: Modifier = Modifier
) {
    val damageRelation = uiState.damageRelation
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        PokemonTypesSelector(
            allTypes = uiState.allTypes,
            primaryType = uiState.selectedPrimaryType,
            secondaryType = uiState.selectedSecondaryType,
            onPrimaryTypeSelected = onPrimaryTypeSelected,
            onSecondaryTypeSelected = onSecondaryTypeSelected
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Damage Taken", modifier = Modifier.align(CenterHorizontally))
        DamageTakenCard(
            damageRelation = uiState.damageRelation
        )
    }
}

@Composable
fun PokemonTypesSelector(
    allTypes: List<Type>,
    primaryType: Type?,
    secondaryType: Type?,
    onPrimaryTypeSelected: (Type?) -> Unit,
    onSecondaryTypeSelected: (Type?) -> Unit
) {
    var primaryTypeExpanded by remember { mutableStateOf(false) }
    var secondaryTypeExpanded by remember { mutableStateOf(false) }

    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Min)
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            typeSelector(
                label = primaryType?.name ?: "Select type",
                expanded = primaryTypeExpanded,
                description = "Primary type",
                selectableTypes = allTypes.filter { it != primaryType },
                onTypeButtonClicked = { primaryTypeExpanded = true },
                onTypeSelected = {
                    onPrimaryTypeSelected(it)
                    primaryTypeExpanded = false
                },
                onMenuDismissed = { primaryTypeExpanded = false }
            )

            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )

            typeSelector(
                label = secondaryType?.name ?: "Select type",
                expanded = secondaryTypeExpanded,
                description = "Secondary type",
                selectableTypes = allTypes.filter { it != primaryType && it != secondaryType },
                onTypeButtonClicked = { secondaryTypeExpanded = true },
                onTypeSelected = {
                    onSecondaryTypeSelected(it)
                    secondaryTypeExpanded = false
                },
                onMenuDismissed = { secondaryTypeExpanded = false }
            )
        }
    }
}

@Composable
fun RowScope.typeSelector(
    label: String,
    expanded: Boolean,
    description: String,
    selectableTypes: List<Type>,
    onTypeButtonClicked: () -> Unit,
    onTypeSelected: (Type?) -> Unit,
    onMenuDismissed: () -> Unit
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onTypeButtonClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelLarge
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onMenuDismissed,
            modifier = Modifier.fillMaxHeight(0.5f)
        ) {
            DropdownMenuItem(
                text = { Text(text = "Clear") },
                onClick = { onTypeSelected(null) }
            )
            selectableTypes.forEach {
                DropdownMenuItem(
                    text = { PokemonType(type = it) },
                    onClick = {
                        onTypeSelected(it)
                    }
                )
            }
        }
        Text(
            text = description,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun DamageTakenCard(
    damageRelation: DamageRelation
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            if (damageRelation.isEmpty) {
                Text(text = "Select a primary or/and secondary type to view damage relations.")
            } else {
                damageRelation(
                    label = "Weak against...",
                    typeDamageFactors = damageRelation.weakAgainstMap.toList()
                )
                damageRelation(
                    label = "Resistant against...",
                    typeDamageFactors = damageRelation.resistantAgainstMap.toList()
                )
                damageRelation(
                    label = "Normal damage from...",
                    typeDamageFactors = damageRelation.normalAgainstMap.toList()
                )
            }
        }
    }
}

@Composable
fun ColumnScope.damageRelation(
    label: String,
    typeDamageFactors: List<Pair<Type, DamageFactor>>
) {
    Text(
        text = label,
        modifier = Modifier
            .align(CenterHorizontally)
            .padding(vertical = 8.dp)
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        typeDamageFactors.chunked(3).forEach { chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                chunk.forEach { (type, damageFactor) ->
                    typeDamageFactor(type, damageFactor)
                }
            }
        }
    }
}

@Composable
fun RowScope.typeDamageFactor(
    type: Type,
    damageFactor: DamageFactor
) {
    PokemonType(type = type, modifier = Modifier.weight(1f))
}

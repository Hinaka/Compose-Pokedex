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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
        allTypes = uiState.allTypes,
        selectedType = uiState.selectedType,
        damageRelations = uiState.damageRelationMap,
        onTypeSelected = typeViewModel::selectType,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun TypeScreen(
    allTypes: List<Type>,
    selectedType: Type?,
    damageRelations: Map<Type, DamageFactor>,
    onTypeSelected: (Type) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Box {
            Button(onClick = { expanded = true }) {
                Text(text = selectedType?.name ?: "Select Type")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxHeight(0.5f)
            ) {
                allTypes.forEach {
                    DropdownMenuItem(
                        text = { PokemonType(type = it) },
                        onClick = {
                            expanded = false
                            onTypeSelected(it)
                        },
                    )
                }
            }
        }
        damageRelations.forEach { (type, damageFactor) ->
            Row(modifier = Modifier.fillMaxWidth()) {
                PokemonType(type = type, modifier = Modifier.weight(1f))
                Text(text = "$damageFactor")
            }
        }
    }
}

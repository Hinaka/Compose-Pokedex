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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
        damageRelations = uiState.damageRelationMap,
        modifier = modifier
    )
}

@Composable
fun TypeScreen(
    damageRelations: Map<Type, DamageFactor>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        damageRelations.forEach { (type, damageFactor) ->
            Row(modifier = Modifier.fillMaxWidth()) {
                PokemonType(type = type, modifier = Modifier.weight(1f))
                Text(text = "${damageFactor.value}")
            }
        }
    }
}

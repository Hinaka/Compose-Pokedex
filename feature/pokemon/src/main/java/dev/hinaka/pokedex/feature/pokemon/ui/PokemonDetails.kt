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
package dev.hinaka.pokedex.feature.pokemon.ui

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.domain.Pokemon

@Composable
fun PokemonDetails(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Log.d("Trung", "pokemon = $pokemon")
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding
    ) {
        item {
            Text(text = pokemon.name)
        }
    }
}

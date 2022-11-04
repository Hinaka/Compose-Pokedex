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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.pokemon.maxStats
import dev.hinaka.pokedex.domain.pokemon.minStats

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
        BaseStatsSection(
            baseStats = pokemon.baseStats,
            minStats = pokemon.minStats,
            maxStats = pokemon.maxStats,
            Modifier.fillMaxWidth()
        )
    }
}

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
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.OutlinedText
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.component.SubLabel
import dev.hinaka.pokedex.core.ui.utils.decimalFormat
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Species
import dev.hinaka.pokedex.feature.pokemon.R.string

@Composable
internal fun SpeciesSection(
    species: Species,
    modifier: Modifier = Modifier
) {
    Section(title = stringResource(string.pokemon_details_species_section), modifier) {
        OutlinedText(text = species.flavorText, Modifier.fillMaxWidth())
        SubLabel(
            text = stringResource(string.pokemon_details_species_pokedex_entry),
            Modifier.align(CenterHorizontally)
        )

        Space(dp = 8.dp)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Column(Modifier.weight(1f)) {
                val (ft, inc) = species.height.ftAndIn
                val m = species.height.meter
                OutlinedText(
                    text = stringResource(
                        string.pokemon_details_species_height_format,
                        ft.decimalFormat(),
                        inc.decimalFormat(),
                        m.decimalFormat()
                    ),
                    Modifier.fillMaxWidth()
                )
                SubLabel(
                    text = stringResource(string.pokemon_details_species_height),
                    Modifier.align(CenterHorizontally)
                )
            }

            Column(Modifier.weight(1f)) {
                val lbs = species.weight.pound
                val kg = species.weight.kilogram
                OutlinedText(
                    text = stringResource(
                        string.pokemon_details_species_weight_format,
                        lbs.decimalFormat(),
                        kg.decimalFormat()
                    ),
                    Modifier.fillMaxWidth()
                )
                SubLabel(
                    text = stringResource(string.pokemon_details_species_weight),
                    Modifier.align(CenterHorizontally)
                )
            }
        }
    }
}

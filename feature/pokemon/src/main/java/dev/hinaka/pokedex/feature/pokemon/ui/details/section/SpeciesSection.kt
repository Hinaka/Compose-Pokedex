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
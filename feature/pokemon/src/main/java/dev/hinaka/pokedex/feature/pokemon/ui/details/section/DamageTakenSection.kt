package dev.hinaka.pokedex.feature.pokemon.ui.details.section

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.hinaka.pokedex.core.ui.type.DamageRelationChart
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.feature.pokemon.R.string

@Composable
internal fun DamageTakenSection(
    damageRelation: Map<Type, DamageFactor>,
    modifier: Modifier = Modifier
) {
    Section(title = stringResource(string.pokemon_details_damage_taken_section), modifier) {
        DamageRelationChart(damageRelationMap = damageRelation, Modifier.fillMaxWidth())
    }
}
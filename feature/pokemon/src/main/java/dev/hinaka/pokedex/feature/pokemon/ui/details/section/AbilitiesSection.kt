package dev.hinaka.pokedex.feature.pokemon.ui.details.section

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.PokedexIcon
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.ui.utils.dp
import dev.hinaka.pokedex.domain.Ability
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Abilities
import dev.hinaka.pokedex.feature.pokemon.R.string
import dev.hinaka.pokedex.feature.pokemon.ui.details.LocalDetailsTheme

@Composable
internal fun AbilitiesSection(
    abilities: Abilities,
    modifier: Modifier = Modifier
) {
    Section(
        title = stringResource(string.pokemon_details_abilities_section),
        modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (abilities.normalAbilities.isNotEmpty()) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    abilities.normalAbilities.forEach {
                        NormalAbility(
                            ability = it,
                            Modifier.weight(1f)
                        )
                    }
                }
            }

            abilities.hiddenAbility?.let {
                HiddenAbility(ability = it, Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun NormalAbility(
    ability: Ability,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .background(
                color = LocalDetailsTheme.current.primaryColor,
                shape = shapes.small
            )
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = ability.name,
            color = LocalDetailsTheme.current.onPrimaryColor,
            modifier = Modifier.weight(1f),
            textAlign = Center,
            style = typography.bodyMedium
        )
        Space(dp = 8.dp)
        PokedexIcon(
            icon = PokedexIcons.Info,
            contentDescription = stringResource(
                string.pokemon_details_abilities_info_description,
                ability.name
            ),
            tint = LocalDetailsTheme.current.onPrimaryColor,
            modifier = Modifier.size(typography.bodyMedium.fontSize.dp)
        )
    }
}

@Composable
private fun HiddenAbility(
    ability: Ability,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .background(
                color = LocalDetailsTheme.current.primaryColor,
                shape = shapes.small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(string.pokemon_details_abilities_hidden),
            color = LocalDetailsTheme.current.onPrimaryColor,
            style = typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        Row(
            Modifier
                .weight(1f)
                .background(
                    color = colorScheme.surface,
                    shape = shapes.small.copy(
                        topStart = ZeroCornerSize,
                        bottomStart = ZeroCornerSize
                    )
                )
                .border(
                    width = 1.dp,
                    color = LocalDetailsTheme.current.primaryColor,
                    shape = shapes.small.copy(
                        topStart = ZeroCornerSize,
                        bottomStart = ZeroCornerSize
                    )
                )
                .padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = ability.name,
                color = colorScheme.onSurface,
                textAlign = Center,
                style = typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            PokedexIcon(
                icon = PokedexIcons.Info,
                contentDescription = stringResource(
                    string.pokemon_details_abilities_info_description,
                    ability.name
                ),
                tint = colorScheme.onSurface,
                modifier = Modifier.size(typography.bodyMedium.fontSize.dp)
            )
        }
    }
}
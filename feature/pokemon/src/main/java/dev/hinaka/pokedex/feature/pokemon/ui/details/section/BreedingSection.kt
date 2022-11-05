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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.OutlinedText
import dev.hinaka.pokedex.core.designsystem.component.PokedexImage
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.component.SubLabel
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.domain.pokemon.EggGroup
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.FEMALE_ONLY
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.GENDERLESS
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.M1_F1
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.M1_F3
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.M1_F7
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.M3_F1
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.M7_F1
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.MALE_ONLY
import dev.hinaka.pokedex.feature.pokemon.R.string
import dev.hinaka.pokedex.feature.pokemon.ui.details.LocalDetailsTheme

@Composable
internal fun BreedingSection(
    breeding: Breeding,
    modifier: Modifier = Modifier
) {
    Section(title = stringResource(string.pokemon_details_breeding_section), modifier) {
        GenderRatio(genderRatio = breeding.genderRatio, Modifier.fillMaxWidth())
        SubLabel(
            text = stringResource(string.pokemon_details_breeding_gender_ratio),
            Modifier.align(CenterHorizontally)
        )

        Space(dp = 8.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            breeding.eggGroups.forEach { EggGroup(it, Modifier.weight(1f)) }
        }
        SubLabel(
            text = stringResource(string.pokemon_details_breeding_egg_groups),
            Modifier.align(CenterHorizontally)
        )

        Space(dp = 8.dp)
        OutlinedText(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = LocalDetailsTheme.current.onPrimaryColor)) {
                    append(breeding.eggCycles.toString())
                }
                append(" ")
                append(
                    stringResource(
                        string.pokemon_details_breeding_steps_to_hatch_format,
                        breeding.stepsToHatch
                    )
                )
            },
            Modifier.fillMaxWidth()
        )
        SubLabel(
            text = stringResource(string.pokemon_details_breeding_egg_cycles),
            Modifier.align(CenterHorizontally)
        )
    }
}

@Composable
private fun GenderRatio(
    genderRatio: GenderRatio,
    modifier: Modifier = Modifier
) {
    val baseShape = MaterialTheme.shapes.small

    when (genderRatio) {
        GENDERLESS -> GenderlessRow(shape = baseShape, modifier)
        MALE_ONLY -> MaleOnlyRow(shape = baseShape, modifier)
        FEMALE_ONLY -> FemaleOnlyRow(shape = baseShape, modifier)
        M1_F7 -> MixGenderRow(shape = baseShape, male = 1, female = 7, modifier)
        M1_F3 -> MixGenderRow(shape = baseShape, male = 1, female = 3, modifier)
        M1_F1 -> MixGenderRow(shape = baseShape, male = 1, female = 1, modifier)
        M3_F1 -> MixGenderRow(shape = baseShape, male = 3, female = 1, modifier)
        M7_F1 -> MixGenderRow(shape = baseShape, male = 7, female = 1, modifier)
    }
}

@Composable
private fun GenderlessRow(shape: CornerBasedShape, modifier: Modifier = Modifier) {
    GenderRow(
        shape = shape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Text(
            text = stringResource(string.pokemon_details_breeding_genderless),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun MaleOnlyRow(shape: CornerBasedShape, modifier: Modifier = Modifier) {
    GenderRow(
        shape = shape,
        color = PokedexTheme.colors.male,
        modifier = modifier
    ) {
        Text(
            text = stringResource(string.pokemon_details_breeding_male_only),
            color = PokedexTheme.colors.onMale
        )
    }
}

@Composable
private fun FemaleOnlyRow(shape: CornerBasedShape, modifier: Modifier = Modifier) {
    GenderRow(
        shape = shape,
        color = PokedexTheme.colors.female,
        modifier = modifier
    ) {
        Text(
            text = stringResource(string.pokemon_details_breeding_female_only),
            color = PokedexTheme.colors.onFemale
        )
    }
}

@Composable
private fun MixGenderRow(
    shape: CornerBasedShape,
    male: Int,
    female: Int,
    modifier: Modifier = Modifier
) {
    val cornerZero = CornerSize(0)
    val maleShape = shape.copy(
        topEnd = cornerZero,
        bottomEnd = cornerZero
    )
    val femaleShape = shape.copy(
        topStart = cornerZero,
        bottomStart = cornerZero
    )

    val showMaleContent = male >= female
    val showFemaleContent = female >= male

    val total = (male + female).toFloat()
    val maleRatio = male / total
    val femaleRatio = female / total

    Row(modifier = modifier.height(Min)) {
        GenderRow(
            shape = maleShape,
            color = PokedexTheme.colors.male,
            modifier = Modifier
                .weight(maleRatio)
                .fillMaxHeight()
        ) {
            if (showMaleContent) {
                PokedexImage(
                    icon = PokedexIcons.Male,
                    contentDescription = "Male icon",
                    colorFilter = ColorFilter.tint(PokedexTheme.colors.onMale),
                    modifier = Modifier.size(24.dp)
                )
                Space(dp = 8.dp)
                Text(
                    text = "%.1f".format(maleRatio * 100) + "%",
                    color = PokedexTheme.colors.onMale
                )
            }
        }
        GenderRow(
            shape = femaleShape,
            color = PokedexTheme.colors.female,
            modifier = Modifier
                .weight(femaleRatio)
                .fillMaxHeight()
        ) {
            if (showFemaleContent) {
                PokedexImage(
                    icon = PokedexIcons.Female,
                    contentDescription = "Female icon",
                    colorFilter = ColorFilter.tint(PokedexTheme.colors.onFemale),
                    modifier = Modifier.size(24.dp)
                )
                Space(dp = 8.dp)
                Text(
                    text = "%.1f".format(femaleRatio * 100) + "%",
                    color = PokedexTheme.colors.onFemale
                )
            }
        }
    }
}

@Composable
private fun GenderRow(
    shape: Shape,
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .background(
                shape = shape,
                color = color
            )
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
private fun EggGroup(
    eggGroup: EggGroup,
    modifier: Modifier = Modifier
) {
    Text(
        text = eggGroup.name,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 8.dp, horizontal = 8.dp)
    )
}

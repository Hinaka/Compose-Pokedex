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

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.PkdxCard
import dev.hinaka.pokedex.core.designsystem.component.PokedexImage
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.core.ui.type.DamageRelationChart
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.FEMALE_ONLY
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.GENDERLESS
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.M1_F1
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.M1_F3
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.M1_F7
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.M3_F1
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.M7_F1
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.MALE_ONLY
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Stats
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type

@Composable
fun ExtraInfoSections(
    pokemon: Pokemon,
    damageRelation: Map<Type, DamageFactor>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Damage taken",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )
        Space(dp = 8.dp)
        PkdxCard(modifier = Modifier.fillMaxWidth()) {
            DamageRelationChart(
                damageRelationMap = damageRelation,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Space(dp = 16.dp)
        SpritesSection(imageUrls = pokemon.imageUrls, Modifier.fillMaxWidth())
        Space(dp = 16.dp)
        Text(
            text = "Training",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )
        Space(dp = 8.dp)
        PkdxCard(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = buildEffortString(pokemon.training.effortYield),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = Dp.Hairline,
                        color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(vertical = 8.dp, horizontal = 8.dp)
            )
            Space(dp = 4.dp)
            Text(
                text = "EV Yield",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Space(dp = 8.dp)
            Text(
                text = "${pokemon.training.catchRate} (" +
                    "%.1f".format(pokemon.training.catchRatePercentAtFullHp) +
                    "% - Pokéball - Full HP)",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = Dp.Hairline,
                        color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(vertical = 8.dp, horizontal = 8.dp)
            )
            Space(dp = 4.dp)
            Text(
                text = "Catch rate",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Space(dp = 8.dp)
            Text(
                text = "${pokemon.training.growthRate.name} " +
                    "(${pokemon.training.growthRate.expToMaxLevel} Experience)",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = Dp.Hairline,
                        color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(vertical = 8.dp, horizontal = 8.dp)
            )
            Space(dp = 4.dp)
            Text(
                text = "Growth rate",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Space(dp = 8.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = pokemon.training.baseHappiness.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = Dp.Hairline,
                                color = MaterialTheme.colorScheme.outline,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    )
                    Space(dp = 4.dp)
                    Text(
                        text = "Base happiness",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = pokemon.training.baseExp.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = Dp.Hairline,
                                color = MaterialTheme.colorScheme.outline,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    )
                    Space(dp = 4.dp)
                    Text(
                        text = "Base experience",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        Space(dp = 16.dp)

        Text(
            text = "Breeding",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )
        Space(dp = 8.dp)
        PkdxCard(modifier = Modifier.fillMaxWidth()) {
            GenderRatio(
                genderRatio = pokemon.breeding.genderRatio,
                modifier = Modifier.fillMaxWidth()
            )
            Space(4.dp)
            Text(
                text = "Gender ratio",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Space(dp = 8.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                pokemon.breeding.eggGroups.forEach {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    )
                }
            }
            Space(dp = 4.dp)
            Text(
                text = "Egg groups",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Space(dp = 8.dp)
            Text(
                text = "${pokemon.breeding.eggCycles} (${pokemon.breeding.stepsToHatch} steps)",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = Dp.Hairline,
                        color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(vertical = 8.dp, horizontal = 8.dp)
            )
            Space(dp = 4.dp)
            Text(
                text = "Egg cycles",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

private fun buildEffortString(effort: Stats) = buildAnnotatedString {
    val stats = mutableListOf<Pair<String, Int>>()
    val addStatIfNotZero: (label: String, value: Int) -> Unit = { label, value ->
        if (value > 0) {
            stats.add(label to value)
        }
    }

    addStatIfNotZero("HP", effort.hp)
    addStatIfNotZero("Attack", effort.attack)
    addStatIfNotZero("Defense", effort.defense)
    addStatIfNotZero("Sp. Attack", effort.specialAttack)
    addStatIfNotZero("Sp. Defense", effort.specialDefense)
    addStatIfNotZero("Speed", effort.speed)

    stats.forEachIndexed { index, (label, value) ->
        append("$value $label")
        if (index != stats.lastIndex) append(" - ")
    }
}

@Composable
private fun GenderRatio(
    genderRatio: GenderRatio,
    modifier: Modifier = Modifier
) {
    val baseShape = MaterialTheme.shapes.small

    when (genderRatio) {
        GENDERLESS -> GenderlessRow(shape = baseShape, modifier = modifier)
        MALE_ONLY -> MaleOnlyRow(shape = baseShape, modifier = modifier)
        FEMALE_ONLY -> FemaleOnlyRow(shape = baseShape, modifier = modifier)
        M1_F7 -> MixGenderRow(shape = baseShape, male = 1, female = 7, modifier = modifier)
        M1_F3 -> MixGenderRow(shape = baseShape, male = 1, female = 3, modifier = modifier)
        M1_F1 -> MixGenderRow(shape = baseShape, male = 1, female = 1, modifier = modifier)
        M3_F1 -> MixGenderRow(shape = baseShape, male = 3, female = 1, modifier = modifier)
        M7_F1 -> MixGenderRow(shape = baseShape, male = 7, female = 1, modifier = modifier)
    }
}

@Composable
private fun GenderlessRow(shape: CornerBasedShape, modifier: Modifier = Modifier) {
    GenderRow(
        shape = shape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Text(text = "Genderless", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun MaleOnlyRow(shape: CornerBasedShape, modifier: Modifier = Modifier) {
    GenderRow(
        shape = shape,
        color = PokedexTheme.colors.male,
        modifier = modifier
    ) {
        Text(text = "Male Only", color = PokedexTheme.colors.onMale)
    }
}

@Composable
private fun FemaleOnlyRow(shape: CornerBasedShape, modifier: Modifier = Modifier) {
    GenderRow(
        shape = shape,
        color = PokedexTheme.colors.female,
        modifier = modifier
    ) {
        Text(text = "Female Only", color = PokedexTheme.colors.onFemale)
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

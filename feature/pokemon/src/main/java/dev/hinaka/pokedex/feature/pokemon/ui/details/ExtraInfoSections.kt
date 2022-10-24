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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.PkdxCard
import dev.hinaka.pokedex.core.designsystem.component.PokedexImage
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.core.ui.type.getTypeContainerColors
import dev.hinaka.pokedex.core.ui.utils.preview.PokedexPreviews
import dev.hinaka.pokedex.core.ui.utils.preview.PokemonPreviewParameterProvider
import dev.hinaka.pokedex.domain.pokemon.GenderRatio
import dev.hinaka.pokedex.domain.pokemon.GenderRatio.FEMALE_ONLY
import dev.hinaka.pokedex.domain.pokemon.GenderRatio.GENDERLESS
import dev.hinaka.pokedex.domain.pokemon.GenderRatio.M1_F1
import dev.hinaka.pokedex.domain.pokemon.GenderRatio.M1_F3
import dev.hinaka.pokedex.domain.pokemon.GenderRatio.M1_F7
import dev.hinaka.pokedex.domain.pokemon.GenderRatio.M3_F1
import dev.hinaka.pokedex.domain.pokemon.GenderRatio.M7_F1
import dev.hinaka.pokedex.domain.pokemon.GenderRatio.MALE_ONLY
import dev.hinaka.pokedex.domain.pokemon.Pokemon

@Composable
fun ExtraInfoSections(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Damage taken",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )
        Space(dp = 8.dp)
        PkdxCard(modifier = Modifier.fillMaxWidth()) {

        }
        Space(dp = 16.dp)
        Text(
            text = "Sprites",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )
        Space(dp = 8.dp)
        PkdxCard(modifier = Modifier.fillMaxWidth()) {

        }
        Space(dp = 16.dp)
        Text(
            text = "Training",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )
        Space(dp = 8.dp)
        PkdxCard(modifier = Modifier.fillMaxWidth()) {

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
            Text(
                text = "${pokemon.breeding.eggCycles} (${pokemon.breeding.steps} steps)",
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

@Composable
private fun GenderRatio(
    genderRatio: GenderRatio,
    modifier: Modifier = Modifier,
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
        modifier = modifier,
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
        bottomStart = cornerZero,
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
                    color = PokedexTheme.colors.onMale,
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
                    color = PokedexTheme.colors.onFemale,
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
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .background(
                shape = shape,
                color = color,
            )
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp,
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@PokedexPreviews
@Composable
private fun GenderRatioPreviews() {
    PokedexTheme {
        PkdxCard(modifier = Modifier.width(1600.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GenderRatio.values().forEach {
                    GenderRatio(genderRatio = it, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@PokedexPreviews
@Composable
private fun ExtraInfoSectionsPreviews(
    @PreviewParameter(PokemonPreviewParameterProvider::class, limit = 1) pokemon: Pokemon
) {
    PokedexTheme {
        val (containerColor, contentColor) = pokemon.types.getTypeContainerColors()

        Surface(color = containerColor, contentColor = contentColor) {
            ExtraInfoSections(pokemon)
        }
    }
}
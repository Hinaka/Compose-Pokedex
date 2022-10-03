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

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.hinaka.pokedex.core.designsystem.component.PokedexIcon
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.icon.Icon
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.core.designsystem.theme.overlaySurface
import dev.hinaka.pokedex.core.ui.pokemon.PokemonId
import dev.hinaka.pokedex.core.ui.pokemon.PokemonName
import dev.hinaka.pokedex.core.ui.type.PokemonTypes
import dev.hinaka.pokedex.core.ui.type.onTypeContainerColor
import dev.hinaka.pokedex.core.ui.type.typeContainerColor
import dev.hinaka.pokedex.core.ui.utils.preview.PokedexPreviews
import dev.hinaka.pokedex.core.ui.utils.preview.PokemonPreviewParameterProvider
import dev.hinaka.pokedex.domain.Ability
import dev.hinaka.pokedex.domain.EmptyAbility
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.pokemon.Height
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.pokemon.Weight
import dev.hinaka.pokedex.domain.pokemon.max
import dev.hinaka.pokedex.domain.pokemon.total
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.feature.pokemon.R
import dev.hinaka.pokedex.feature.pokemon.ui.PokemonDetailsTab.INFO
import dev.hinaka.pokedex.feature.pokemon.ui.PokemonDetailsTab.MENU
import dev.hinaka.pokedex.feature.pokemon.ui.PokemonDetailsTab.MORE
import dev.hinaka.pokedex.feature.pokemon.ui.PokemonDetailsTab.MOVES

@Composable
fun PokemonDetails(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    var selectedIndex by remember { mutableStateOf(0) }

    val primaryType = pokemon.types.firstOrNull()
    val containerColor = primaryType?.typeContainerColor ?: colorScheme.surfaceVariant
    val contentColor = primaryType?.onTypeContainerColor ?: colorScheme.onSurfaceVariant

    Surface(
        modifier = modifier.padding(contentPadding),
        color = containerColor,
        contentColor = contentColor
    ) {
        Column {
            PokemonHeader(
                id = pokemon.id,
                name = pokemon.name,
                types = pokemon.types,
                imageUrl = pokemon.imageUrl,
                containerColor = containerColor,
                contentColor = contentColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            TabContent(
                tab = PokemonDetailsTab.values()[selectedIndex],
                pokemon = pokemon,
                containerColor = containerColor,
                contentColor = contentColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            )
            PokemonTabRow(
                selectedIndex = selectedIndex,
                onTabChanged = { selectedIndex = it },
                modifier = Modifier.fillMaxWidth(),
                containerColor = containerColor,
                contentColor = contentColor
            )
        }
    }
}

@Composable
private fun PokemonHeader(
    id: Id,
    name: String,
    types: List<Type>,
    imageUrl: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Row(
            modifier = Modifier
                .height(Min)
                .background(colorScheme.overlaySurface)
                .padding(start = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Center
            ) {
                Row(
                    verticalAlignment = CenterVertically
                ) {
                    PokemonName(
                        name = name,
                        modifier = Modifier.weight(1f),
                        style = typography.headlineMedium
                    )
                    Space(dp = 8.dp)
                    PokemonId(
                        id = id,
                        style = typography.headlineSmall
                    )
                }
                PokemonTypes(
                    types = types,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
            Space(dp = 8.dp)
            PokemonImage(
                imageUrl = imageUrl,
                imageDescription = stringResource(
                    id = R.string.pokemon_image_description,
                    name
                ),
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}

@Composable
private fun PokemonImage(
    imageUrl: String,
    imageDescription: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = colorScheme.overlaySurface,
        shape = RoundedCornerShape(
            topStartPercent = 50,
            bottomStartPercent = 50
        )
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = imageDescription,
            placeholder = painterResource(PokedexIcons.PokeBall.id),
            modifier = Modifier
                .defaultMinSize(minHeight = 120.dp)
                .padding(start = 16.dp)
        )
    }
}

@Composable
private fun TabContent(
    tab: PokemonDetailsTab,
    pokemon: Pokemon,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    when (tab) {
        INFO -> PokemonInfoTab(
            pokemon = pokemon,
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = modifier
        )
        MOVES -> PokemonMoves(
            modifier = modifier
        )
        MORE -> PokemonExtraInfo(
            modifier = modifier
        )
        MENU -> PokemonMenu(
            modifier = modifier
        )
    }
}

@Composable
private fun PokemonInfoTab(
    pokemon: Pokemon,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        speciesSection(
            flavorText = pokemon.flavorText,
            height = pokemon.height,
            weight = pokemon.weight
        )

        Space(dp = 16.dp)

        abilitiesSection(
            normalAbilities = pokemon.normalAbilities,
            hiddenAbility = pokemon.hiddenAbility,
            containerColor = containerColor,
            contentColor = contentColor
        )

        Space(dp = 16.dp)
        Text(
            text = "Base Stats",
            modifier = Modifier.align(CenterHorizontally),
            style = typography.titleMedium
        )
        Space(dp = 8.dp)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = spacedBy(8.dp)
            ) {
                val maxStat = pokemon.baseStats.max.toFloat()
                val containerColor = pokemon.types.first().typeContainerColor
                val contentColor = pokemon.types.first().onTypeContainerColor
                StatRow(
                    label = "HP",
                    value = pokemon.baseStats.hp,
                    valueRatio = pokemon.baseStats.hp / maxStat,
                    containerColor = containerColor,
                    contentColor = contentColor,
                    modifier = Modifier.fillMaxWidth()
                )
                StatRow(
                    label = "Attack",
                    value = pokemon.baseStats.attack,
                    valueRatio = pokemon.baseStats.attack / maxStat,
                    containerColor = containerColor,
                    contentColor = contentColor,
                    modifier = Modifier.fillMaxWidth()
                )
                StatRow(
                    label = "Defense",
                    value = pokemon.baseStats.defense,
                    valueRatio = pokemon.baseStats.defense / maxStat,
                    containerColor = containerColor,
                    contentColor = contentColor,
                    modifier = Modifier.fillMaxWidth()
                )
                StatRow(
                    label = "Sp.Attack",
                    value = pokemon.baseStats.specialAttack,
                    valueRatio = pokemon.baseStats.specialAttack / maxStat,
                    containerColor = containerColor,
                    contentColor = contentColor,
                    modifier = Modifier.fillMaxWidth()
                )
                StatRow(
                    label = "Sp.Defense",
                    value = pokemon.baseStats.specialDefense,
                    valueRatio = pokemon.baseStats.specialDefense / maxStat,
                    containerColor = containerColor,
                    contentColor = contentColor,
                    modifier = Modifier.fillMaxWidth()
                )
                StatRow(
                    label = "Speed",
                    value = pokemon.baseStats.speed,
                    valueRatio = pokemon.baseStats.speed / maxStat,
                    containerColor = containerColor,
                    contentColor = contentColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = buildAnnotatedString {
                        append("TOTAL: ")
                        withStyle(SpanStyle(color = containerColor)) {
                            append(pokemon.baseStats.total.toString())
                        }
                    },
                    modifier = Modifier.align(CenterHorizontally),
                    style = typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.speciesSection(
    flavorText: String,
    height: Height,
    weight: Weight,
) {
    SectionTitle(title = "Species")
    Space(dp = 8.dp)
    SectionCard {
        OutlinedText(text = flavorText, modifier = Modifier.fillMaxWidth())
        Space(dp = 4.dp)
        Text(
            text = "Pokedex entry",
            style = typography.labelMedium,
            modifier = Modifier.align(CenterHorizontally)
        )

        Space(dp = 8.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                OutlinedText(
                    text = "%.2f".format(height.meter) + " m",
                    modifier = Modifier.fillMaxWidth()
                )
                Space(dp = 4.dp)
                Text(
                    text = "Height",
                    style = typography.labelMedium,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }
            Column(
                modifier = Modifier.weight(1f),
            ) {
                OutlinedText(
                    text = "%.2f".format(weight.kg) + " kg",
                    modifier = Modifier.fillMaxWidth()
                )
                Space(dp = 4.dp)
                Text(
                    text = "Weight",
                    style = typography.labelMedium,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.abilitiesSection(
    normalAbilities: List<Ability>,
    hiddenAbility: Ability,
    containerColor: Color,
    contentColor: Color,
) {
    SectionTitle(title = "Abilities")
    Space(dp = 8.dp)
    SectionCard {
        if (normalAbilities.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = spacedBy(8.dp)
            ) {
                normalAbilities.forEach {
                    AbilityItem(
                        ability = it,
                        containerColor = containerColor,
                        contentColor = contentColor
                    )
                }
            }
            Space(dp = 8.dp)
        }

        if (hiddenAbility != EmptyAbility) {
            HiddenAbilityItem(
                ability = hiddenAbility,
                containerColor = containerColor,
                contentColor = contentColor
            )
        }
    }
}

@Composable
private fun AbilityItem(
    ability: Ability,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .background(
                color = containerColor,
                shape = shapes.small
            )
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = CenterVertically
    ) {
        Text(
            text = ability.name,
            color = contentColor,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = typography.bodyMedium
        )
        Space(dp = 8.dp)
        PokedexIcon(
            icon = PokedexIcons.Info,
            contentDescription = "",
            tint = contentColor
        )
    }
}

@Composable
private fun HiddenAbilityItem(
    ability: Ability,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .background(
                color = containerColor,
                shape = shapes.small
            ),
        verticalAlignment = CenterVertically
    ) {
        Text(
            text = "Hidden",
            color = contentColor,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            textAlign = TextAlign.Center,
            style = typography.bodyMedium,
        )
        Row(
            modifier = Modifier
                .weight(1f)
                .background(
                    color = colorScheme.surface.copy(alpha = 0.4f),
                    shape = shapes.small.copy(
                        topStart = CornerSize(0),
                        bottomStart = CornerSize(0)
                    )
                )
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = ability.name,
                color = contentColor,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = typography.bodyMedium,
            )
            Space(dp = 8.dp)
            PokedexIcon(
                icon = PokedexIcons.Info,
                contentDescription = "",
                tint = contentColor
            )
        }

    }
}

@Composable
private fun ColumnScope.SectionTitle(
    title: String,
) {
    Text(
        text = title,
        modifier = Modifier.align(CenterHorizontally),
        style = typography.titleMedium
    )
}

@Composable
private fun ColumnScope.SectionCard(
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(1f),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
private fun OutlinedText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = typography.bodyMedium,
        textAlign = TextAlign.Center,
        modifier = modifier
            .border(
                width = Dp.Hairline,
                color = colorScheme.outline,
                shape = shapes.small
            )
            .padding(vertical = 8.dp, horizontal = 8.dp),
    )
}

@Composable
private fun StatRow(
    label: String,
    value: Int,
    valueRatio: Float,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier,
) {
    Row(modifier = modifier) {
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .background(
                    color = containerColor,
                    shape = shapes.small.copy(
                        topEnd = CornerSize(0),
                        bottomEnd = CornerSize(0)
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = contentColor,
            textAlign = TextAlign.Center,
            style = typography.bodyMedium
        )
        Text(
            text = value.toString(),
            modifier = Modifier
                .fillMaxWidth(valueRatio)
                .background(
                    color = containerColor.copy(alpha = 0.6f),
                    shape = shapes.small.copy(
                        topStart = CornerSize(0),
                        bottomStart = CornerSize(0)
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = contentColor,
            textAlign = TextAlign.End,
            style = typography.bodyMedium
        )
    }
}

@Composable
fun PokemonMoves(
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(text = "Moves")
    }
}

@Composable
fun PokemonExtraInfo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(text = "More")
    }
}

@Composable
fun PokemonMenu(
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(text = "Menu")
    }
}

@Composable
private fun PokemonTabRow(
    selectedIndex: Int,
    onTabChanged: (Int) -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        indicator = {},
        divider = {},
    ) {
        PokemonDetailsTab.values().mapIndexed { index, tab ->
            PokemonTab(
                selected = index == selectedIndex,
                onClick = { onTabChanged(index) },
                label = tab.label,
                icon = tab.icon
            )
        }
    }
}

@Composable
private fun PokemonTab(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    icon: Icon,
) {
    val selectedColor = LocalContentColor.current
    val unselectedColor = selectedColor.copy(alpha = 0.4f)
    Tab(
        selected = selected,
        onClick = onClick,
        selectedContentColor = selectedColor,
        unselectedContentColor = unselectedColor
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .height(48.dp)
                .fillMaxWidth(),
            verticalArrangement = Center,
            horizontalAlignment = CenterHorizontally
        ) {
            PokedexIcon(
                icon = icon,
                contentDescription = stringResource(
                    id = R.string.pokemon_details_tab_icon_description,
                    label
                )
            )
            if (selected) {
                Text(
                    text = label,
                    style = typography.bodyLarge
                )
            }
        }
    }
}

private enum class PokemonDetailsTab(
    val icon: Icon,
    val labelId: Int,
) {
    INFO(
        icon = PokedexIcons.Info,
        labelId = R.string.pokemon_details_tab_info
    ),
    MOVES(
        icon = PokedexIcons.Move,
        labelId = R.string.pokemon_details_tab_moves
    ),
    MORE(
        icon = PokedexIcons.Plus,
        labelId = R.string.pokemon_details_tab_more
    ),
    MENU(
        icon = PokedexIcons.Menu,
        labelId = R.string.pokemon_details_tab_menu
    );

    val label @Composable get() = stringResource(id = labelId)
}

@PokedexPreviews
@Composable
private fun PokemonHeaderPreviews(
    @PreviewParameter(PokemonPreviewParameterProvider::class, limit = 1) pokemon: Pokemon
) {
    PokedexTheme {
        val containerColor = pokemon.types.first().typeContainerColor
        val contentColor = pokemon.types.first().onTypeContainerColor

        PokemonHeader(
            id = pokemon.id,
            name = pokemon.name,
            types = pokemon.types,
            imageUrl = pokemon.imageUrl,
            containerColor = containerColor,
            contentColor = contentColor
        )
    }
}

@PokedexPreviews
@Composable
private fun PokemonTabRowPreviews(
    @PreviewParameter(PokemonPreviewParameterProvider::class, limit = 1) pokemon: Pokemon
) {
    PokedexTheme {
        val containerColor = pokemon.types.first().typeContainerColor
        val contentColor = pokemon.types.first().onTypeContainerColor

        Column(verticalArrangement = spacedBy(8.dp)) {
            PokemonDetailsTab.values().forEachIndexed { index, _ ->
                PokemonTabRow(
                    selectedIndex = index,
                    onTabChanged = {},
                    containerColor = containerColor,
                    contentColor = contentColor
                )
            }
        }
    }
}

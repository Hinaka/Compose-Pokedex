package dev.hinaka.pokedex.feature.pokemon.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.hinaka.pokedex.core.designsystem.component.PkdxCard
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.core.designsystem.theme.overlaySurface
import dev.hinaka.pokedex.core.ui.pokemon.PokemonId
import dev.hinaka.pokedex.core.ui.pokemon.PokemonName
import dev.hinaka.pokedex.core.ui.type.PokemonTypes
import dev.hinaka.pokedex.core.ui.type.getTypeContainerColors
import dev.hinaka.pokedex.core.ui.utils.preview.PokedexPreviews
import dev.hinaka.pokedex.core.ui.utils.preview.PokemonPreviewParameterProvider
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.feature.pokemon.R.string

@Composable
fun MenuSections(
    previousPokemon: Pokemon?,
    nextPokemon: Pokemon?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Navigation",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )
        Space(dp = 8.dp)
        PkdxCard {
            Text(
                text = "Tap the buttons below to go to the next or previous Pokémon on the list",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
            nextPokemon?.let {
                Space(dp = 8.dp)
                Text(
                    text = "Next Pokémon",
                    modifier = Modifier.align(Alignment.End),
                    style = MaterialTheme.typography.titleMedium,
                )
                Space(dp = 4.dp)
                PokemonCard(
                    id = it.id,
                    name = it.name,
                    types = it.types,
                    imageUrl = it.imageUrl,
                )
            }
            previousPokemon?.let {
                Space(dp = 8.dp)
                Text(
                    text = "Previous Pokémon",
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.titleMedium,
                )
                Space(dp = 4.dp)
                PokemonCard(
                    id = it.id,
                    name = it.name,
                    types = it.types,
                    imageUrl = it.imageUrl,
                )
            }
        }
    }
}

@Composable
private fun PokemonCard(
    id: Id,
    name: String,
    types: List<Type>,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    val (containerColor, contentColor) = types.getTypeContainerColors()
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
                .background(MaterialTheme.colorScheme.overlaySurface)
                .padding(start = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PokemonName(
                        name = name,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Space(dp = 8.dp)
                    PokemonId(
                        id = id,
                        style = MaterialTheme.typography.headlineSmall
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
                    id = string.pokemon_image_description,
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
        color = MaterialTheme.colorScheme.overlaySurface,
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

@PokedexPreviews
@Composable
private fun MenuSectionsPreviews(
    @PreviewParameter(PokemonPreviewParameterProvider::class, limit = 1) pokemon: Pokemon
) {
    PokedexTheme {
        val (containerColor, contentColor) = pokemon.types.getTypeContainerColors()

        MenuSections(
            previousPokemon = pokemon,
            nextPokemon = pokemon
        )
    }
}
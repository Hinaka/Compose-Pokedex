package dev.hinaka.pokedex.feature.pokemon.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.PkdxCard
import dev.hinaka.pokedex.core.designsystem.component.PkdxOutlinedButton
import dev.hinaka.pokedex.core.designsystem.component.PokedexImage
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.core.ui.pokemon.PokemonInfoCard
import dev.hinaka.pokedex.core.ui.type.getTypeContainerColors
import dev.hinaka.pokedex.core.ui.type.typeColor
import dev.hinaka.pokedex.core.ui.utils.preview.PokedexPreviews
import dev.hinaka.pokedex.core.ui.utils.preview.PokemonPreviewParameterProvider
import dev.hinaka.pokedex.domain.pokemon.Pokemon

@Composable
fun MenuSections(
    previousPokemon: Pokemon?,
    nextPokemon: Pokemon?,
    typeColor: Color,
    onSelectPokemon: (Pokemon) -> Unit,
    onSelectHome: () -> Unit,
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
            PkdxOutlinedButton(
                onClick = onSelectHome,
                icon = PokedexIcons.Home,
                label = "Home",
                modifier = Modifier.fillMaxWidth(),
                color = typeColor,
            )
            Space(dp = 4.dp)
            Text(
                text = "Tap the home button to close all previous Pokémon screens and return to the main screen.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
            Space(dp = 8.dp)
            Text(
                text = "Tap the buttons below to go to the next or previous Pokémon on the list",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
            nextPokemon?.let {
                NextPokemonNavigation(pokemon = it, modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSelectPokemon(it)
                    })
            }
            previousPokemon?.let {
                PreviousPokemonNavigation(
                    pokemon = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectPokemon(it) })
            }
        }
    }
}

@Composable
private fun NextPokemonNavigation(
    pokemon: Pokemon,
    modifier: Modifier,
) {
    val color = pokemon.types.first().typeColor

    Column(modifier = modifier.padding(top = 16.dp)) {
        Row(
            modifier = Modifier.align(Alignment.End),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Next Pokémon",
                color = color,
                style = MaterialTheme.typography.titleMedium,
            )
            PokedexImage(
                icon = PokedexIcons.ArrowForward,
                contentDescription = "Navigate to next pokemon named ${pokemon.name}",
                colorFilter = ColorFilter.tint(color)
            )
        }
        Space(dp = 4.dp)
        with(pokemon) {
            PokemonInfoCard(
                id = id,
                name = name,
                genus = genus,
                types = types,
                imageUrl = imageUrl,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun PreviousPokemonNavigation(
    pokemon: Pokemon,
    modifier: Modifier,
) {
    val color = pokemon.types.first().typeColor

    Column(modifier = modifier.padding(top = 16.dp)) {
        Row(
            modifier = Modifier.align(Alignment.Start),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            PokedexImage(
                icon = PokedexIcons.ArrowBack,
                contentDescription = "Navigate to previous pokemon named ${pokemon.name}",
                colorFilter = ColorFilter.tint(color)
            )
            Text(
                text = "Previous Pokémon",
                color = color,
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Space(dp = 4.dp)
        with(pokemon) {
            PokemonInfoCard(
                id = id,
                name = name,
                genus = genus,
                types = types,
                imageUrl = imageUrl,
                modifier = Modifier.fillMaxWidth()
            )
        }
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
            nextPokemon = pokemon,
            typeColor = pokemon.types.first().typeColor,
            onSelectPokemon = {},
            onSelectHome = {},
        )
    }
}
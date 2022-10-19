package dev.hinaka.pokedex.feature.pokemon.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.PkdxCard
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.core.ui.pokemon.PokemonInfoCard
import dev.hinaka.pokedex.core.ui.type.getTypeContainerColors
import dev.hinaka.pokedex.core.ui.utils.preview.PokedexPreviews
import dev.hinaka.pokedex.core.ui.utils.preview.PokemonPreviewParameterProvider
import dev.hinaka.pokedex.domain.pokemon.Pokemon

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
                PokemonInfoCard(
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
                PokemonInfoCard(
                    id = it.id,
                    name = it.name,
                    types = it.types,
                    imageUrl = it.imageUrl,
                )
            }
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
            nextPokemon = pokemon
        )
    }
}
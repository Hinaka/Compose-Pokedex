package dev.hinaka.pokedex.feature.pokemon.ui.details.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.PkdxOutlinedButton
import dev.hinaka.pokedex.core.designsystem.component.PokedexImage
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.ui.pokemon.PokemonInfoCard
import dev.hinaka.pokedex.core.ui.type.typeColor
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.feature.pokemon.R.string
import dev.hinaka.pokedex.feature.pokemon.ui.details.LocalDetailsTheme

@Composable
internal fun NavigationSection(
    previousPokemon: Pokemon?,
    nextPokemon: Pokemon?,
    onNavigateHome: () -> Unit,
    onChangePokemon: (Pokemon) -> Unit,
    modifier: Modifier = Modifier
) {
    Section(title = stringResource(string.pokemon_details_navigation_section), modifier) {
        PkdxOutlinedButton(
            onClick = onNavigateHome,
            icon = PokedexIcons.Home,
            label = "Home",
            color = LocalDetailsTheme.current.onPrimaryColor,
            modifier = Modifier.fillMaxWidth()
        )
        Space(dp = 4.dp)
        Text(
            text = stringResource(string.pokemon_details_navigation_home_explain),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
        Space(dp = 8.dp)
        Text(
            text = stringResource(string.pokemon_details_navigation_go_to_explain),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
        nextPokemon?.let {
            NextPokemonNavigation(
                pokemon = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onChangePokemon(it) }
            )
        }
        previousPokemon?.let {
            PreviousPokemonNavigation(
                pokemon = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onChangePokemon(it) }
            )
        }
    }
}

@Composable
private fun NextPokemonNavigation(
    pokemon: Pokemon,
    modifier: Modifier
) {
    val color = pokemon.types.first().typeColor

    Column(modifier = modifier.padding(top = 16.dp)) {
        Row(
            modifier = Modifier.align(Alignment.End),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(string.pokemon_details_navigate_next_pokemon),
                color = color,
                style = MaterialTheme.typography.titleMedium
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
                imageUrl = pokemon.imageUrls.officialArtwork,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun PreviousPokemonNavigation(
    pokemon: Pokemon,
    modifier: Modifier
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
                text = stringResource(string.pokemon_details_navigate_previous_pokemon),
                color = color,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Space(dp = 4.dp)
        with(pokemon) {
            PokemonInfoCard(
                id = id,
                name = name,
                genus = genus,
                types = types,
                imageUrl = pokemon.imageUrls.officialArtwork,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
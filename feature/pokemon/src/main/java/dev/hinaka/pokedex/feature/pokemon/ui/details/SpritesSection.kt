package dev.hinaka.pokedex.feature.pokemon.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.domain.pokemon.Pokemon.ImageUrls
import dev.hinaka.pokedex.feature.pokemon.R.string

private val spriteSize = 120.dp

@Composable
internal fun SpritesSection(
    imageUrls: ImageUrls,
    modifier: Modifier = Modifier,
) {
    Section(title = stringResource(string.pokemon_details_sprites_section), modifier) {
        Text(
            text = "Normal",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Space(dp = 4.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AsyncImage(
                model = imageUrls.frontDefault,
                contentDescription = "Front default sprite of pokemon",
                placeholder = painterResource(PokedexIcons.PokeBall.id),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size(spriteSize)
            )
            AsyncImage(
                model = imageUrls.backDefault,
                contentDescription = "Back default sprite of pokemon",
                placeholder = painterResource(PokedexIcons.PokeBall.id),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size(spriteSize)
            )
        }

        Space(dp = 8.dp)

        Text(
            text = "Shiny",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Space(dp = 4.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AsyncImage(
                model = imageUrls.frontShiny,
                contentDescription = "Front shiny sprite of pokemon",
                placeholder = painterResource(PokedexIcons.PokeBall.id),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size(spriteSize)
            )
            AsyncImage(
                model = imageUrls.backShiny,
                contentDescription = "Back shiny sprite of pokemon",
                placeholder = painterResource(PokedexIcons.PokeBall.id),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size(spriteSize)
            )
        }
    }
}
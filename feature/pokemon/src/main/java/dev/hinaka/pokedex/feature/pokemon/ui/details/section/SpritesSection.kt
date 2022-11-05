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
    modifier: Modifier = Modifier
) {
    Section(title = stringResource(string.pokemon_details_sprites_section), modifier) {
        Text(
            text = stringResource(string.pokemon_details_sprites_normal),
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
            text = stringResource(string.pokemon_details_sprites_shiny),
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

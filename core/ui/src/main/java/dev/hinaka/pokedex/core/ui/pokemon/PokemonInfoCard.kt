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
package dev.hinaka.pokedex.core.ui.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.hinaka.pokedex.core.designsystem.component.PkdxCard
import dev.hinaka.pokedex.core.designsystem.component.PkdxCardDefaults
import dev.hinaka.pokedex.core.designsystem.component.Space
import dev.hinaka.pokedex.core.designsystem.icon.PokedexIcons
import dev.hinaka.pokedex.core.designsystem.theme.surfaceOverlay
import dev.hinaka.pokedex.core.ui.type.PokemonTypes
import dev.hinaka.pokedex.core.ui.type.getTypeContainerColors
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.type.Type

@Composable
fun PokemonInfoCard(
    id: Id,
    name: String,
    genus: String,
    types: List<Type>,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    val (containerColor, contentColor) = types.getTypeContainerColors()
    PkdxCard(
        modifier = modifier.height(Min),
        contentPadding = PaddingValues(0.dp),
        colors = PkdxCardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceOverlay)
        ) {
            PokemonInfo(
                id = id,
                name = name,
                genus = genus,
                types = types,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
            )
            Space(dp = 8.dp)
            PokemonImage(
                url = imageUrl,
                description = "Image of pokemon named $name",
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}

@Composable
private fun PokemonInfo(
    id: Id,
    name: String,
    genus: String,
    types: List<Type>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            PokemonName(
                name = name,
                modifier = Modifier.weight(1f),
                isLarge = true
            )
            Space(dp = 4.dp)
            PokemonId(id = id)
        }
        Space(dp = 8.dp)
        Text(text = genus, style = MaterialTheme.typography.titleMedium)
        Space(dp = 8.dp)
        PokemonTypes(types = types)
    }
}

@Composable
private fun PokemonImage(
    url: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceOverlay,
        shape = RoundedCornerShape(
            topStartPercent = 50,
            bottomStartPercent = 50
        )
    ) {
        AsyncImage(
            model = url,
            contentDescription = description,
            placeholder = painterResource(PokedexIcons.PokeBall.id),
            modifier = Modifier
                .padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
                .size(120.dp)
        )
    }
}

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
package dev.hinaka.pokedex.core.ui.type

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.PokedexImage
import dev.hinaka.pokedex.core.designsystem.theme.PokedexTheme
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.domain.type.Type.Identifier

@Composable
fun PokemonTypes(
    types: List<Type>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        types.forEach { type ->
            PokemonType(
                type = type,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun PokemonType(
    type: Type,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(1.dp, type.typeColor),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.Transparent,
            contentColor = type.typeColor
        )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            PokedexImage(
                icon = type.icon,
                contentDescription = "icon of type ${type.name}",
                modifier = Modifier.size(16.dp),
                colorFilter = ColorFilter.tint(type.typeColor)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = type.name.uppercase(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun PokemonTypeWithDamageFactor(
    type: Type,
    damageFactor: DamageFactor,
    modifier: Modifier = Modifier,
    showDamageFactor: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonType(type = type, modifier = Modifier.fillMaxWidth())
        if (showDamageFactor) {
            Text(
                text = "x ${damageFactor.value / 100f}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Composable
fun PokemonTypePreview() {
    PokedexTheme {
        Column(
            modifier = Modifier.width(Min),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Identifier.values().forEachIndexed { index, identifier ->
                PokemonType(
                    type = Type(
                        id = Id(index),
                        name = identifier.name,
                        identifier = identifier
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

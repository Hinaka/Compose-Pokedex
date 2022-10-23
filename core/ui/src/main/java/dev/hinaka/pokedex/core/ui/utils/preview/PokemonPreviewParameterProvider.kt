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
package dev.hinaka.pokedex.core.ui.utils.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.domain.type.Type.Identifier.GRASS
import dev.hinaka.pokedex.domain.type.Type.Identifier.POISON

class PokemonPreviewParameterProvider : PreviewParameterProvider<Pokemon> {
    override val values: Sequence<Pokemon>
        get() = sequenceOf(
            Pokemon(
                id = Id(1),
                name = "Bulbasaur",
                types = listOf(
                    Type(
                        id = Id(12),
                        identifier = GRASS,
                        name = "Grass"
                    ),
                    Type(
                        id = Id(4),
                        identifier = POISON,
                        name = "Poison"
                    )
                ),
                genus = "Seed Pokémon"
            )
        )
}

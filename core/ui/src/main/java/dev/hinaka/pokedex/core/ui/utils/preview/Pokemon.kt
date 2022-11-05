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
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.pokemon.pokemon
import dev.hinaka.pokedex.domain.type.Type

class PokemonProvider : PreviewParameterProvider<Pokemon> {
    override val values: Sequence<Pokemon>
        get() = sequenceOf(
            generatePokemon(1)
        )
}

class PokemonsProvider : PreviewParameterProvider<List<Pokemon>> {
    override val values: Sequence<List<Pokemon>>
        get() = sequenceOf(
            (1..10).map { generatePokemon(it) }
        )
}

private fun generatePokemon(id: Int) = pokemon(id) {
    name = "Pokemon $id"
    genus = "Genus $id"

    val types = Type.Identifier.values()
    val typeIndex = id % types.size
    type(typeIndex) {
        identifier = types[typeIndex]
        name = types[typeIndex].name
    }

    if (id % 2 != 0) {
        val secondaryTypeIndex = (typeIndex + 1) % types.size
        type(secondaryTypeIndex) {
            identifier = types[secondaryTypeIndex]
            name = types[secondaryTypeIndex].name
        }
    }
}

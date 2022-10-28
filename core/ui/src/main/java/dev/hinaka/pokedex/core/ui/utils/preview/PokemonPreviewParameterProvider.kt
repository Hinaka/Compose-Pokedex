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

class PokemonPreviewParameterProvider : PreviewParameterProvider<Pokemon> {
    override val values: Sequence<Pokemon>
        get() = generateSequence(1) {
            it.inc()
        }.map {
            pokemon(it) {
                name = "Pokemon $it"

                val types = Type.Identifier.values()
                val typeIndex = it % types.size
                type(typeIndex) {
                    name = types[typeIndex].name
                    identifier = types[typeIndex]
                }
            }
        }
}

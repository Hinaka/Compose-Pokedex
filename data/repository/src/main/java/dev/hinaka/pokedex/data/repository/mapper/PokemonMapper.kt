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
package dev.hinaka.pokedex.data.repository.mapper

import dev.hinaka.pokedex.data.database.model.pokemon.PokemonEntity
import dev.hinaka.pokedex.data.database.model.pokemon.PokemonWithTypes
import dev.hinaka.pokedex.data.database.model.xref.PokemonTypeXRef
import dev.hinaka.pokedex.data.network.model.NetworkPokemon
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.domain.Stats

fun PokemonWithTypes.toDomain() = Pokemon(
    id = Id(pokemon.id),
    name = pokemon.name.orEmpty(),
    types = types.sortedBy { it.slot }.mapNotNull { it.type.toDomain() },
    imageUrl = pokemon.imageUrl.orEmpty(),
    abilities = emptyList(),
    baseStats = Stats(0, 0, 0, 0, 0, 0),
    baseMoves = emptyList()
)

fun NetworkPokemon.toEntity() = PokemonEntity(
    id = id ?: -1,
    name = name,
    imageUrl = imageUrl
)

fun NetworkPokemon.toPokemonTypeXRef(): List<PokemonTypeXRef> {
    val list = mutableListOf<PokemonTypeXRef>()

    val pokemonId = id ?: return list
    types?.forEach {
        val typeId = it.type?.id
        if (typeId != null) {
            list.add(
                PokemonTypeXRef(
                    pokemonId = pokemonId,
                    typeId = typeId,
                    slot = it.slot,
                )
            )
        }
    }

    return list
}

fun List<PokemonWithTypes>.toDomain() = map { it.toDomain() }

fun List<NetworkPokemon>.toEntity() = map { it.toEntity() }

fun List<NetworkPokemon>.toPokemonTypeXRef() = flatMap { it.toPokemonTypeXRef() }

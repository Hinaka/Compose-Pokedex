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

import dev.hinaka.pokedex.data.database.model.PokemonEntity
import dev.hinaka.pokedex.data.network.model.NetworkPokemon
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.domain.Stats

fun PokemonEntity.toDomain() = Pokemon(
    id = Id(id),
    name = name.orEmpty(),
    types = emptyList(),
    imageUrl = imageUrl.orEmpty(),
    abilities = emptyList(),
    baseStats = Stats(0, 0, 0, 0, 0, 0),
    baseMoves = emptyList()
)

fun NetworkPokemon.toEntity() = PokemonEntity(
    id = id ?: -1,
    name = name,
    imageUrl = imageUrl,
)

fun List<PokemonEntity>.toDomain() = map { it.toDomain() }

fun List<NetworkPokemon>.toEntity() = map { it.toEntity() }

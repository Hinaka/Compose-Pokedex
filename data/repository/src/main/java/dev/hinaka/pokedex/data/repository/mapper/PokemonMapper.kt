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
import dev.hinaka.pokedex.data.database.model.xref.PokemonTypeXRef
import dev.hinaka.pokedex.data.network.model.NetworkPokemon

fun NetworkPokemon.toEntity() = PokemonEntity(
    id = id,
    name = name,
    imageUrl = imageUrl,
    type1Id = typeIds?.firstOrNull(),
    type2Id = typeIds?.lastOrNull(),
    ability1Id = normalAbilityIds?.firstOrNull(),
    ability2Id = normalAbilityIds?.lastOrNull(),
    hiddenAbilityId = hiddenAbilityId,
    flavorText = flavorText,
    height = height,
    weight = weight,
    hp = baseHp,
    attack = baseAttack,
    spAttack = baseSpAttack,
    defense = baseDefense,
    spDefense = baseSpDefense,
    speed = baseSpeed
)

fun NetworkPokemon.toPokemonTypeXRef(): List<PokemonTypeXRef> = typeIds.orEmpty().map {
    PokemonTypeXRef(
        pokemonId = id,
        typeId = it
    )
}

fun List<NetworkPokemon>.toEntity() = map { it.toEntity() }

fun List<NetworkPokemon>.toPokemonTypeXRef() = flatMap { it.toPokemonTypeXRef() }

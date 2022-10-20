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
import dev.hinaka.pokedex.data.database.model.xref.PokemonMoveXRef
import dev.hinaka.pokedex.data.database.model.xref.PokemonTypeXRef
import dev.hinaka.pokedex.data.network.model.NetworkPokemon

fun NetworkPokemon.toEntity() = PokemonEntity(
    id = id,
    name = name,
    imageUrl = imageUrl,
    type1Id = typeIds?.getOrNull(0),
    type2Id = typeIds?.getOrNull(1),
    ability1Id = normalAbilityIds?.getOrNull(0),
    ability2Id = normalAbilityIds?.getOrNull(1),
    hiddenAbilityId = hiddenAbilityId,
    flavorText = flavorText,
    height = height,
    weight = weight,
    hp = baseHp,
    attack = baseAttack,
    spAttack = baseSpAttack,
    defense = baseDefense,
    spDefense = baseSpDefense,
    speed = baseSpeed,
    genus = genus,
)

fun NetworkPokemon.toPokemonTypeXRef(): List<PokemonTypeXRef> = typeIds.orEmpty().map {
    PokemonTypeXRef(
        pokemonId = id,
        typeId = it
    )
}

fun NetworkPokemon.toPokemonMoveXRef(): List<PokemonMoveXRef> =
    learnableMoves.orEmpty().mapNotNull { learnableMove ->
        learnableMove.moveId?.let {
            PokemonMoveXRef(
                pokemonId = id,
                moveId = it,
                learnLevel = learnableMove.learnLevel,
                learnMethod = learnableMove.learnMethod,
            )
        }

    }

fun List<NetworkPokemon>.toEntity() = map { it.toEntity() }

fun List<NetworkPokemon>.toPokemonTypeXRef() = flatMap { it.toPokemonTypeXRef() }

fun List<NetworkPokemon>.toPokemonMoveXRef() = flatMap { it.toPokemonMoveXRef() }
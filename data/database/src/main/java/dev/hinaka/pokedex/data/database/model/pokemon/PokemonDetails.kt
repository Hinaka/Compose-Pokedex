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
package dev.hinaka.pokedex.data.database.model.pokemon

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import dev.hinaka.pokedex.data.database.model.AbilityEntity
import dev.hinaka.pokedex.data.database.model.move.MoveEntity
import dev.hinaka.pokedex.data.database.model.toDomain
import dev.hinaka.pokedex.data.database.model.type.TypeEntity
import dev.hinaka.pokedex.data.database.model.type.toDomain
import dev.hinaka.pokedex.data.database.model.xref.PokemonMoveXRef
import dev.hinaka.pokedex.domain.EmptyAbility
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.pokemon.EmptyHeight
import dev.hinaka.pokedex.domain.pokemon.EmptyWeight
import dev.hinaka.pokedex.domain.pokemon.Height
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.pokemon.Stats
import dev.hinaka.pokedex.domain.pokemon.Weight

data class PokemonDetails(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "type_1_id",
        entityColumn = "id"
    )
    val type1: TypeEntity?,
    @Relation(
        parentColumn = "type_2_id",
        entityColumn = "id"
    )
    val type2: TypeEntity?,
    @Relation(
        parentColumn = "ability_1_id",
        entityColumn = "id"
    )
    val ability1: AbilityEntity?,
    @Relation(
        parentColumn = "ability_2_id",
        entityColumn = "id"
    )
    val ability2: AbilityEntity?,
    @Relation(
        parentColumn = "hidden_ability_id",
        entityColumn = "id"
    )
    val hiddenAbility: AbilityEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemon_id",
        projection = ["move_id"],
        entity = PokemonMoveXRef::class
    )
    val learnableMoveIds: List<Int>?
)

fun PokemonDetails.toDomain() = Pokemon(
    id = Id(pokemon.id),
    name = pokemon.name.orEmpty(),
    types = listOfNotNull(type1?.toDomain(), type2?.toDomain()),
    imageUrl = pokemon.imageUrl.orEmpty(),
    normalAbilities = listOfNotNull(ability1?.toDomain(), ability2?.toDomain()),
    hiddenAbility = hiddenAbility?.toDomain() ?: EmptyAbility,
    height = pokemon.height?.let { Height.decimeter(it) } ?: EmptyHeight,
    weight = pokemon.weight?.let { Weight.hectogram(it) } ?: EmptyWeight,
    flavorText = pokemon.flavorText.orEmpty(),
    baseStats = Stats(
        hp = pokemon.hp ?: 0,
        attack = pokemon.attack ?: 0,
        defense = pokemon.defense ?: 0,
        specialAttack = pokemon.spAttack ?: 0,
        specialDefense = pokemon.spDefense ?: 0,
        speed = pokemon.speed ?: 0
    )
)

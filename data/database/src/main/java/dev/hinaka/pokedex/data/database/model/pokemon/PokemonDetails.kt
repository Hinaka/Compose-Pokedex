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
import dev.hinaka.pokedex.data.database.model.toDomain
import dev.hinaka.pokedex.data.database.model.type.TypeEntity
import dev.hinaka.pokedex.data.database.model.type.toDomain
import dev.hinaka.pokedex.data.database.model.xref.PokemonEggGroupXRef
import dev.hinaka.pokedex.data.database.model.xref.PokemonMoveXRef
import dev.hinaka.pokedex.domain.EmptyAbility
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.pokemon.Breeding
import dev.hinaka.pokedex.domain.pokemon.EmptyBreeding
import dev.hinaka.pokedex.domain.pokemon.EmptyHeight
import dev.hinaka.pokedex.domain.pokemon.EmptyWeight
import dev.hinaka.pokedex.domain.pokemon.GrowthRate
import dev.hinaka.pokedex.domain.pokemon.Height
import dev.hinaka.pokedex.domain.pokemon.ImageUrls
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.pokemon.Stats
import dev.hinaka.pokedex.domain.pokemon.Training
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
    val learnableMoveIds: List<Int>?,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PokemonEggGroupXRef::class,
            parentColumn = "pokemon_id",
            entityColumn = "egg_group_id"
        )
    )
    val eggGroups: List<EggGroupEntity>?
)

fun PokemonDetails.toDomain() = Pokemon(
    id = Id(pokemon.id),
    name = pokemon.name.orEmpty(),
    types = listOfNotNull(type1?.toDomain(), type2?.toDomain()),
    imageUrls = ImageUrls(
        officialArtwork = pokemon.officialArtwork.orEmpty(),
        frontDefault = pokemon.frontDefault.orEmpty(),
        backDefault = pokemon.backDefault.orEmpty(),
        frontShiny = pokemon.frontShiny.orEmpty(),
        backShiny = pokemon.backShiny.orEmpty()
    ),
    normalAbilities = listOfNotNull(ability1?.toDomain(), ability2?.toDomain()),
    hiddenAbility = hiddenAbility?.toDomain() ?: EmptyAbility,
    height = pokemon.height?.let { Height.decimeter(it) } ?: EmptyHeight,
    weight = pokemon.weight?.let { Weight.hectogram(it) } ?: EmptyWeight,
    flavorText = pokemon.flavorText.orEmpty(),
    baseStats = Stats(
        hp = pokemon.baseStats?.hp ?: 0,
        attack = pokemon.baseStats?.attack ?: 0,
        defense = pokemon.baseStats?.defense ?: 0,
        specialAttack = pokemon.baseStats?.spAttack ?: 0,
        specialDefense = pokemon.baseStats?.spDefense ?: 0,
        speed = pokemon.baseStats?.speed ?: 0
    ),
    genus = pokemon.genus.orEmpty(),
    breeding = if (pokemon.breeding?.eggCycles != null && pokemon.breeding.genderRation != null) {
        Breeding(
            genderRatio = pokemon.breeding.genderRation,
            eggGroups = eggGroups.orEmpty().map { it.toDomain() },
            eggCycles = pokemon.breeding.eggCycles
        )
    } else EmptyBreeding,
    training = Training(
        effort = Stats(
            hp = pokemon.effortStats?.hp ?: 0,
            attack = pokemon.effortStats?.attack ?: 0,
            defense = pokemon.effortStats?.defense ?: 0,
            specialAttack = pokemon.effortStats?.spAttack ?: 0,
            specialDefense = pokemon.effortStats?.spDefense ?: 0,
            speed = pokemon.effortStats?.speed ?: 0
        ),
        catchRate = pokemon.catchRate ?: 0,
        baseExp = pokemon.baseExp ?: 0,
        baseHappiness = pokemon.baseHappiness ?: 0,
        growthRate = GrowthRate( //TODO: map growh rate
            description = "",
            maxExp = 0
        )
    )
)

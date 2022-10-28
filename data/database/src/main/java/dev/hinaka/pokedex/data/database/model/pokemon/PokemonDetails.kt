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
import dev.hinaka.pokedex.data.database.model.growthrate.GrowthRateEntity
import dev.hinaka.pokedex.data.database.model.type.TypeEntity
import dev.hinaka.pokedex.data.database.model.xref.PokemonEggGroupXRef
import dev.hinaka.pokedex.data.database.model.xref.PokemonGrowthRateXRef
import dev.hinaka.pokedex.data.database.model.xref.PokemonMoveXRef
import dev.hinaka.pokedex.domain.pokemon.pokemon

data class PokemonDetails(
    @Embedded val pokemonEntity: PokemonEntity,
    @Relation(
        parentColumn = "type_1_id",
        entityColumn = "id"
    )
    val primaryTypeEntity: TypeEntity?,
    @Relation(
        parentColumn = "type_2_id",
        entityColumn = "id"
    )
    val secondaryTypeEntity: TypeEntity?,
    @Relation(
        parentColumn = "ability_1_id",
        entityColumn = "id"
    )
    val firstAbilityEntity: AbilityEntity?,
    @Relation(
        parentColumn = "ability_2_id",
        entityColumn = "id"
    )
    val secondAbilityEntity: AbilityEntity?,
    @Relation(
        parentColumn = "hidden_ability_id",
        entityColumn = "id"
    )
    val hiddenAbilityEntity: AbilityEntity?,
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
    val eggGroupEntities: List<EggGroupEntity>?,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PokemonGrowthRateXRef::class,
            parentColumn = "pokemon_id",
            entityColumn = "growth_rate_id"
        )
    )
    val growthRateEntity: GrowthRateEntity?
)

fun PokemonDetails.toDomain() = pokemon(pokemonEntity.id) {
    name = pokemonEntity.name
    genus = pokemonEntity.genus

    primaryTypeEntity?.let {
        type(it.id) {
            name = it.name
            identifier = it.identifier
        }
    }

    secondaryTypeEntity?.let {
        type(it.id) {
            name = it.name
            identifier = it.identifier
        }
    }

    imageUrls {
        officialArtwork = pokemonEntity.officialArtwork
        frontDefault = pokemonEntity.frontDefault
        frontShiny = pokemonEntity.frontShiny
        backDefault = pokemonEntity.backDefault
        backShiny = pokemonEntity.backShiny
    }

    abilities {
        firstAbilityEntity?.let {
            normal(it.id) {
                name = it.name
                effect = it.effect
            }
        }

        secondAbilityEntity?.let {
            normal(it.id) {
                name = it.name
                effect = it.effect
            }
        }

        hiddenAbilityEntity?.let {
            hidden(it.id) {
                name = it.name
                effect = it.effect
            }
        }
    }

    species {
        flavorText = pokemonEntity.flavorText
        heightInDecimeter = pokemonEntity.height
        weightInHectogram = pokemonEntity.weight
    }

    pokemonEntity.baseStats?.let {
        baseStats {
            hp = it.hp
            attack = it.attack
            spAttack = it.spAttack
            defense = it.defense
            spDefense = it.spDefense
            speed = it.speed
        }
    }

    pokemonEntity.breeding?.let {
        breeding {
            eggCycles = it.eggCycles
            genderRatio = it.genderRation

            eggGroupEntities.orEmpty().map {
                eggGroup(it.id) {
                    name = it.name
                }
            }
        }
    }

    training {
        catchRate = pokemonEntity.catchRate
        baseExp = pokemonEntity.baseExp
        baseHappiness = pokemonEntity.baseHappiness

        pokemonEntity.effortStats?.let {
            effort {
                hp = it.hp
                attack = it.attack
                spAttack = it.spAttack
                defense = it.defense
                spDefense = it.spDefense
                speed = it.speed
            }
        }

        growthRateEntity?.let {
            growthRate {
                name = it.description
                expToMaxLevel = it.maxExp
            }
        }
    }
}

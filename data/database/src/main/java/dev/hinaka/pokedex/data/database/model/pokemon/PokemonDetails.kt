package dev.hinaka.pokedex.data.database.model.pokemon

import androidx.room.Embedded
import androidx.room.Relation
import dev.hinaka.pokedex.data.database.model.AbilityEntity
import dev.hinaka.pokedex.data.database.model.toDomain
import dev.hinaka.pokedex.data.database.model.type.TypeEntity
import dev.hinaka.pokedex.data.database.model.type.toDomain
import dev.hinaka.pokedex.domain.EmptyAbility
import dev.hinaka.pokedex.domain.EmptyHeight
import dev.hinaka.pokedex.domain.EmptyWeight
import dev.hinaka.pokedex.domain.Height
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.domain.Weight

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
    val hiddenAbility: AbilityEntity?
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
)
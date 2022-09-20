package dev.hinaka.pokedex.data.database.model.pokemon

import androidx.room.Embedded
import androidx.room.Relation
import dev.hinaka.pokedex.data.database.model.type.TypeEntity
import dev.hinaka.pokedex.data.database.model.type.toDomain
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.domain.Stats

data class PokemonWithTypes(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "type_1_id",
        entityColumn = "id",
    )
    val type1: TypeEntity?,
    @Relation(
        parentColumn = "type_2_id",
        entityColumn = "id",

        )
    val type2: TypeEntity?,
)

fun PokemonWithTypes.toDomain() = Pokemon(
    id = Id(pokemon.id),
    name = pokemon.name.orEmpty(),
    types = listOfNotNull(type1?.toDomain(), type2?.toDomain()),
    imageUrl = pokemon.imageUrl.orEmpty(),
    abilities = emptyList(),
    baseStats = Stats(0, 0, 0, 0, 0, 0),
    baseMoves = emptyList()
)

fun List<PokemonWithTypes>.toDomain() = map { it.toDomain() }

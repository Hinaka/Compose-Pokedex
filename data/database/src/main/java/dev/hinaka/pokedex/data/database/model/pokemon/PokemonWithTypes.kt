package dev.hinaka.pokedex.data.database.model.pokemon

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import dev.hinaka.pokedex.data.database.model.type.TypeEntity
import dev.hinaka.pokedex.data.database.model.type.TypeWithSlot
import dev.hinaka.pokedex.data.database.model.type.toDomain
import dev.hinaka.pokedex.data.database.model.xref.PokemonTypeXRef
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.domain.Stats

data class PokemonWithTypes(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        entity = TypeEntity::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            PokemonTypeXRef::class,
            parentColumn = "pokemon_id",
            entityColumn = "type_id"
        )
    )
    val types: List<TypeWithSlot>?,
)

fun PokemonWithTypes.toDomain() = Pokemon(
    id = Id(pokemon.id),
    name = pokemon.name.orEmpty(),
    types = types.orEmpty().toDomain(),
    imageUrl = pokemon.imageUrl.orEmpty(),
    abilities = emptyList(),
    baseStats = Stats(0, 0, 0, 0, 0, 0),
    baseMoves = emptyList()
)

fun List<PokemonWithTypes>.toDomain() = map { it.toDomain() }

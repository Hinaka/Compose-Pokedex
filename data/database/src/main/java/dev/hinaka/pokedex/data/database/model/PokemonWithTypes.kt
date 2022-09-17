package dev.hinaka.pokedex.data.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PokemonWithTypes(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            PokemonTypeXRef::class,
            parentColumn = "pokemon_id",
            entityColumn = "type_id"
        )
    )
    val types: List<TypeEntity>,
)


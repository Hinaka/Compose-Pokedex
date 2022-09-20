package dev.hinaka.pokedex.data.database.model.pokemon

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import dev.hinaka.pokedex.data.database.model.type.TypeEntity
import dev.hinaka.pokedex.data.database.model.type.TypeWithSlot
import dev.hinaka.pokedex.data.database.model.xref.PokemonTypeXRef

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
    val types: List<TypeWithSlot>,
)


package dev.hinaka.pokedex.data.database.model.type

import androidx.room.Embedded
import androidx.room.Relation
import dev.hinaka.pokedex.data.database.model.xref.PokemonTypeXRef

data class TypeWithSlot(
    @Embedded val type: TypeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "type_id",
        entity = PokemonTypeXRef::class,
        projection = ["slot"],
    )
    val slot: Int?,
)

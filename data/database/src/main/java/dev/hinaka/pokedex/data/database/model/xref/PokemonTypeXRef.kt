package dev.hinaka.pokedex.data.database.model.xref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "pokemon_type_xref",
    primaryKeys = ["pokemon_id", "type_id"],
)
data class PokemonTypeXRef(
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "type_id") val typeId: Int,
)

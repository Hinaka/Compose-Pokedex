package dev.hinaka.pokedex.data.database.model.xref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "pokemon_egg_group_xref",
    primaryKeys = ["pokemon_id", "egg_group_id"]
)
data class PokemonEggGroupXRef(
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "egg_group_id") val eggGroupId: Int
)

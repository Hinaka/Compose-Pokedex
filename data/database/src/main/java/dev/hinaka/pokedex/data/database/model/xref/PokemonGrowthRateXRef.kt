package dev.hinaka.pokedex.data.database.model.xref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "pokemon_growth_rate_xref",
    primaryKeys = ["pokemon_id", "growth_rate_id"]
)
data class PokemonGrowthRateXRef(
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "growth_rate_id") val growthRateId: Int,
)

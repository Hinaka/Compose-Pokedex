package dev.hinaka.pokedex.data.database.model.pokemon

import androidx.room.ColumnInfo
import dev.hinaka.pokedex.domain.pokemon.GenderRatio

data class PokemonBreeding(
    @ColumnInfo(name = "gender_ratio") val genderRation: GenderRatio?,
    @ColumnInfo(name = "egg_cycles") val eggCycles: Int?,
)

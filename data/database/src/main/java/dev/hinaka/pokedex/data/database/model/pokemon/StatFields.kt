package dev.hinaka.pokedex.data.database.model.pokemon

import androidx.room.ColumnInfo

data class StatFields(
    @ColumnInfo(name = "hp") val hp: Int?,
    @ColumnInfo(name = "attack") val attack: Int?,
    @ColumnInfo(name = "sp_attack") val spAttack: Int?,
    @ColumnInfo(name = "defense") val defense: Int?,
    @ColumnInfo(name = "sp_defense") val spDefense: Int?,
    @ColumnInfo(name = "speed") val speed: Int?,
)

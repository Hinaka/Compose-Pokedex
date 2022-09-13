package dev.hinaka.pokedex.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.hinaka.pokedex.domain.DamageClass
import dev.hinaka.pokedex.domain.Type

@Entity(tableName = "moves")
data class MoveEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "type") val type: Type?,
    @ColumnInfo(name = "damage_class") val damageClass: DamageClass?,
    @ColumnInfo(name = "power") val power: Int?,
    @ColumnInfo(name = "acc") val acc: Int?,
    @ColumnInfo(name = "pp") val pp: Int?,
)

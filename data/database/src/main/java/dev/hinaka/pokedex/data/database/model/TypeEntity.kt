package dev.hinaka.pokedex.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "types")
data class TypeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
)
package dev.hinaka.pokedex.data.database.model.growthrate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "growth_rates")
data class GrowthRateEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "max_exp") val maxExp: Int?,
)

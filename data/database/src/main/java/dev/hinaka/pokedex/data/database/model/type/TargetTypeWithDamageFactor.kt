package dev.hinaka.pokedex.data.database.model.type

import androidx.room.ColumnInfo
import androidx.room.Relation

data class TargetTypeWithDamageFactor(
    @ColumnInfo(name = "target_type_id") val targetTypeId: Int,
    @ColumnInfo(name = "damage_factor") val damageFactor: Int?,
    @Relation(
        parentColumn = "target_type_id",
        entityColumn = "id",
    )
    val targetType: TypeEntity,
)

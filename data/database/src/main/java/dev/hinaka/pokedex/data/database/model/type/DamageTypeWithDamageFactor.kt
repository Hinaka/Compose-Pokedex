package dev.hinaka.pokedex.data.database.model.type

import androidx.room.ColumnInfo
import androidx.room.Relation

data class DamageTypeWithDamageFactor(
    @ColumnInfo(name = "damage_type_id") val damageTypeId: Int,
    @ColumnInfo(name = "damage_factor") val damageFactor: Int?,
    @Relation(
        parentColumn = "damage_type_id",
        entityColumn = "id",
    )
    val damageType: TypeEntity,
)
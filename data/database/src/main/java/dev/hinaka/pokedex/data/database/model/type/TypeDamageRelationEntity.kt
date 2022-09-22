package dev.hinaka.pokedex.data.database.model.type

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "type_damage_relations",
    primaryKeys = ["type_id", "target_type_id"],
)
data class TypeDamageRelationEntity(
    @ColumnInfo(name = "type_id") val typeId: Int,
    @ColumnInfo(name = "target_type_id") val targetTypeId: Int,
    @ColumnInfo(name = "damage_factor") val damageFactor: Int?,
)
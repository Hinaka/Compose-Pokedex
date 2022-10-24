package dev.hinaka.pokedex.data.database.model.pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.pokemon.EggGroup

@Entity(tableName = "egg_groups")
data class EggGroupEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
)

fun EggGroupEntity.toDomain() = EggGroup(
    id = Id(id),
    name = name.orEmpty()
)

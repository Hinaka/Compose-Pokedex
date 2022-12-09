package dev.hinaka.pokedex.data.database.model.move

import androidx.room.Embedded
import androidx.room.Relation
import dev.hinaka.pokedex.data.database.model.type.TypeEntity
import dev.hinaka.pokedex.data.database.model.type.toDomain
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.move.Move

data class MoveWithType(
    @Embedded val moveEntity: MoveEntity,
    @Relation(
        parentColumn = "type_id",
        entityColumn = "id"
    )
    val typeEntity: TypeEntity
)

fun MoveWithType.toDomain() = Move(
    id = Id(moveEntity.id),
    name = moveEntity.name.orEmpty(),
    type = typeEntity.toDomain()!!,
    damageClass = moveEntity.damageClass!!,
    power = moveEntity.power ?: 0,
    acc = moveEntity.acc ?: 0,
    pp = moveEntity.pp ?: 0
)


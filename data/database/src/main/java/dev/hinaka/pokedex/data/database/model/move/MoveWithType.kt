package dev.hinaka.pokedex.data.database.model.move

import androidx.room.Embedded
import androidx.room.Relation
import dev.hinaka.pokedex.data.database.model.type.TypeEntity
import dev.hinaka.pokedex.domain.move.move

data class MoveWithType(
    @Embedded val moveEntity: MoveEntity,
    @Relation(
        parentColumn = "type_id",
        entityColumn = "id"
    )
    val typeEntity: TypeEntity
)

fun MoveWithType.toDomain() = move(moveEntity.id) {
    name = moveEntity.name
    type(typeEntity.id) {
        name = typeEntity.name
        identifier = typeEntity.identifier
    }
    damageClass = moveEntity.damageClass
    power = moveEntity.power
    acc = moveEntity.acc
    pp = moveEntity.pp
}


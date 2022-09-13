package dev.hinaka.pokedex.data.repository.mapper

import dev.hinaka.pokedex.data.database.model.ItemEntity
import dev.hinaka.pokedex.data.database.model.MoveEntity
import dev.hinaka.pokedex.data.network.model.NetworkItem
import dev.hinaka.pokedex.data.network.model.NetworkMove
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Move

fun MoveEntity.toDomain() = Move(
    id = Id(id),
    name = name.orEmpty(),
    type = type!!,
    damageClass = damageClass!!,
    power = power ?: 0,
    acc = acc ?: 0,
    pp = pp ?: 0,
)

fun NetworkMove.toEntity() = MoveEntity(
    id = id ?: -1,
    name = name,
    type = domainType,
    damageClass = domainDamageClass,
    power = power,
    acc = accuracy,
    pp = pp,
)

fun List<MoveEntity>.toDomain() = map { it.toDomain() }

fun List<NetworkMove>.toEntity() = map { it.toEntity() }

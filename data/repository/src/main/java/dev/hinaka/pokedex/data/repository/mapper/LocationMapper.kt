package dev.hinaka.pokedex.data.repository.mapper

import dev.hinaka.pokedex.data.database.model.LocationEntity
import dev.hinaka.pokedex.data.network.model.NetworkLocation
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Location

fun LocationEntity.toDomain() = Location(
    id = Id(id),
    name = name.orEmpty(),
    region = region.orEmpty(),
)

fun NetworkLocation.toEntity() = LocationEntity(
    id = id ?: -1,
    name = name,
    region = regionName,
)

fun List<LocationEntity>.toDomain() = map { it.toDomain() }

fun List<NetworkLocation>.toEntity() = map { it.toEntity() }

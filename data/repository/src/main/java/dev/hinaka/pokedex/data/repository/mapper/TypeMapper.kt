package dev.hinaka.pokedex.data.repository.mapper

import dev.hinaka.pokedex.data.database.model.TypeEntity
import dev.hinaka.pokedex.data.network.model.NetworkType
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.PokemonType

fun TypeEntity.toDomain() = PokemonType(
    id = Id(id),
    name = name.orEmpty(),
)

fun NetworkType.toEntity() = TypeEntity(
    id = id ?: -1,
    name = name,
)

fun List<TypeEntity>.toDomain() = map { it.toDomain() }

fun List<NetworkType>.toEntity() = map { it.toEntity() }
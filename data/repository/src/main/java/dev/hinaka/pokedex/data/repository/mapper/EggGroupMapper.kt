package dev.hinaka.pokedex.data.repository.mapper

import dev.hinaka.pokedex.data.database.model.pokemon.EggGroupEntity
import dev.hinaka.pokedex.data.network.model.NetworkEggGroup

fun NetworkEggGroup.toEntity() = id?.let {
    EggGroupEntity(
        id = it,
        name = name,
    )
}

fun List<NetworkEggGroup>.toEntity() = mapNotNull { it.toEntity() }
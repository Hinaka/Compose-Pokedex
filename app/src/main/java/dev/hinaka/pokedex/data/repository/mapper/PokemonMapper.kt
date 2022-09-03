package dev.hinaka.pokedex.data.repository.mapper

import dev.hinaka.pokedex.data.network.model.NetworkPokemon
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.domain.Stats

fun NetworkPokemon.toDomain() = Pokemon(
    id = Id(id ?: -1),
    name = name.orEmpty(),
    types = emptyList(),
    imageUrl = "Image",
    abilities = emptyList(),
    baseStats = Stats(0, 0, 0, 0, 0, 0),
    baseMoves = emptyList(),
)
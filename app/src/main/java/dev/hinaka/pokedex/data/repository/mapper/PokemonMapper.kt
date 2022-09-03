package dev.hinaka.pokedex.data.repository.mapper

import dev.hinaka.pokedex.data.database.model.PokemonEntity
import dev.hinaka.pokedex.data.network.model.NetworkPokemon
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.domain.Stats

fun PokemonEntity.toDomain() = Pokemon(
    id = Id(id),
    name = name.orEmpty(),
    types = emptyList(),
    imageUrl = "Image",
    abilities = emptyList(),
    baseStats = Stats(0, 0, 0, 0, 0, 0),
    baseMoves = emptyList(),
)

fun NetworkPokemon.toEntity() = PokemonEntity(
    id = id ?: -1,
    name = name,
)

fun List<PokemonEntity>.toDomain() = map { it.toDomain() }

fun List<NetworkPokemon>.toEntity() = map { it.toEntity() }
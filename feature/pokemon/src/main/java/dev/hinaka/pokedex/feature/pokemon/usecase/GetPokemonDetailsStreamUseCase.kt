package dev.hinaka.pokedex.feature.pokemon.usecase

import dev.hinaka.pokedex.data.repository.PokemonRepository
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import kotlinx.coroutines.flow.Flow

typealias GetPokemonDetailsStreamUseCase =
    @JvmSuppressWildcards (id: Id) -> Flow<Pokemon>

fun getPokemonDetailsStream(
    repository: PokemonRepository,
    id: Id,
) = repository.getPokemonDetailsStream(id)
package dev.hinaka.pokedex.feature.pokemon.usecase

import dev.hinaka.pokedex.data.repository.PokemonRepository
import dev.hinaka.pokedex.domain.Pokemon
import kotlinx.coroutines.flow.Flow

typealias GetPokemonsUseCase = @JvmSuppressWildcards suspend () -> Flow<List<Pokemon>>

fun getPokemons(
    repository: PokemonRepository,
) = repository.getPokemonsStream()
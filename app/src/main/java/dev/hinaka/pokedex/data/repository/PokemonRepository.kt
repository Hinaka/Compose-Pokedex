package dev.hinaka.pokedex.data.repository

import dev.hinaka.pokedex.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonsStream(): Flow<List<Pokemon>>
}
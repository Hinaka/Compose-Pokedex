package dev.hinaka.pokedex.data.repository

import dev.hinaka.pokedex.domain.PokemonType

interface TypeRepository {
    suspend fun getTypes()
}
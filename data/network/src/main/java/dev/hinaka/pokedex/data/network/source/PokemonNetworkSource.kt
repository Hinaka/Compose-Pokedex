package dev.hinaka.pokedex.data.network.source

import dev.hinaka.pokedex.data.network.model.NetworkPokemon

interface PokemonNetworkSource {
    suspend fun getPokemons(offset: Int, limit: Int): List<NetworkPokemon>
}
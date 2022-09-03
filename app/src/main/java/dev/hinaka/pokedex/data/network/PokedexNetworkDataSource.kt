package dev.hinaka.pokedex.data.network

import dev.hinaka.pokedex.data.network.model.NetworkPokemon

interface PokedexNetworkDataSource {
    suspend fun getPokemons(): List<NetworkPokemon>
}
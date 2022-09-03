package dev.hinaka.pokedex.data.network.retrofit

import android.util.Log
import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.network.model.NetworkPokemon
import dev.hinaka.pokedex.data.network.retrofit.api.PokemonApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class RetrofitPokedexNetworkDataSource @Inject constructor(
    private val pokemonApi: PokemonApi,
) : PokedexNetworkDataSource {
    override suspend fun getPokemons(): List<NetworkPokemon> = coroutineScope {
        val ids = pokemonApi.getPokemons().results.orEmpty().mapNotNull { it.id }
        ids.map {
            async { pokemonApi.getPokemon(it) }
        }.awaitAll()
    }
}
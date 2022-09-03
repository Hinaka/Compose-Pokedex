package dev.hinaka.pokedex.data.network.retrofit.api

import dev.hinaka.pokedex.data.network.model.NetworkPagedResponse
import dev.hinaka.pokedex.data.network.model.NetworkPokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(): NetworkPagedResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): NetworkPokemon
}
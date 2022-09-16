package dev.hinaka.pokedex.data.network.retrofit.api

import dev.hinaka.pokedex.data.network.model.NetworkPagedResponse
import dev.hinaka.pokedex.data.network.model.NetworkType
import retrofit2.http.GET
import retrofit2.http.Path

interface TypeApi {

    @GET("type")
    suspend fun getTypes(): NetworkPagedResponse

    @GET("type/{id}")
    suspend fun getType(@Path("id") id: Int): NetworkType
}
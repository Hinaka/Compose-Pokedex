package dev.hinaka.pokedex.data.network.retrofit.api

import dev.hinaka.pokedex.data.network.model.NetworkMove
import dev.hinaka.pokedex.data.network.model.NetworkPagedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoveApi {

    @GET("move")
    suspend fun getMoves(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): NetworkPagedResponse

    @GET("move/{id}")
    suspend fun getMove(@Path("id") id: Int): NetworkMove
}
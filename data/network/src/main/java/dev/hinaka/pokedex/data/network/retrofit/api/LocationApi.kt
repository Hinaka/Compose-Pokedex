package dev.hinaka.pokedex.data.network.retrofit.api

import dev.hinaka.pokedex.data.network.model.NetworkLocation
import dev.hinaka.pokedex.data.network.model.NetworkPagedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApi {

    @GET("location")
    suspend fun getLocations(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): NetworkPagedResponse

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: Int): NetworkLocation
}
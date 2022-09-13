package dev.hinaka.pokedex.data.network.retrofit.api

import dev.hinaka.pokedex.data.network.model.NetworkItem
import dev.hinaka.pokedex.data.network.model.NetworkPagedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItemApi {

    @GET("item")
    suspend fun getItems(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): NetworkPagedResponse

    @GET("item/{id}")
    suspend fun getItem(@Path("id") id: Int): NetworkItem
}
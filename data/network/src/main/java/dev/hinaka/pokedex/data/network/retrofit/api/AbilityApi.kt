package dev.hinaka.pokedex.data.network.retrofit.api

import dev.hinaka.pokedex.data.network.model.NetworkAbility
import dev.hinaka.pokedex.data.network.model.NetworkPagedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AbilityApi {

    @GET("ability")
    suspend fun getAbilities(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): NetworkPagedResponse

    @GET("ability/{id}")
    suspend fun getAbility(@Path("id") id: Int): NetworkAbility
}
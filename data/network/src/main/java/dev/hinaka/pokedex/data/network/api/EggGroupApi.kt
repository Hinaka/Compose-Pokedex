package dev.hinaka.pokedex.data.network.api

import dev.hinaka.pokedex.data.network.response.egggroup.GetEggGroupResponse
import dev.hinaka.pokedex.data.network.response.egggroup.GetEggGroupsResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface EggGroupApi {
    @GET("egg-group?limit=100000&offset=0")
    suspend fun getAllEggGroups(): GetEggGroupsResponse

    @GET("egg-group/{id}")
    suspend fun getEggGroup(@Path("id") id: Int): GetEggGroupResponse
}
package dev.hinaka.pokedex.data.network.api

import dev.hinaka.pokedex.data.network.response.growthrate.GetGrowthRateResponse
import dev.hinaka.pokedex.data.network.response.growthrate.GetGrowthRatesResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GrowthRateApi {
    @GET("growth-rate?limit=100000&offset=0")
    suspend fun getAllGrowthRates(): GetGrowthRatesResponse

    @GET("growth-rate/{id}")
    suspend fun getGrowthRate(@Path("id") id: Int): GetGrowthRateResponse
}
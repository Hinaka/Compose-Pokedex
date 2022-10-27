package dev.hinaka.pokedex.data.network.retrofit

import dev.hinaka.pokedex.data.network.api.GrowthRateApi
import dev.hinaka.pokedex.data.network.datasource.GrowthRatesNetworkSource
import dev.hinaka.pokedex.data.network.model.NetworkGrowthRate
import dev.hinaka.pokedex.data.network.response.common.id
import dev.hinaka.pokedex.data.network.response.common.isEn
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class RetrofitGrowthRateNetworkSource @Inject constructor(
    private val growthRateApi: GrowthRateApi,
) : GrowthRatesNetworkSource {

    override suspend fun getAllGrowthRates(): List<NetworkGrowthRate> = coroutineScope {
        val ids = growthRateApi.getAllGrowthRates().results.orEmpty().mapNotNull { it.id }

        ids.map {
            async { getGrowthRate(it) }
        }.awaitAll()
    }

    private suspend fun getGrowthRate(id: Int): NetworkGrowthRate {
        val response = growthRateApi.getGrowthRate(id)

        return NetworkGrowthRate(
            id = response.id,
            description = response.descriptions?.first { it.language.isEn }?.description,
            maxExp = response.levels?.first { it.level == 100 }?.experience,
        )
    }
}
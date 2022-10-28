/*
 * Copyright 2022 Hinaka (Trung Nguyễn Minh Trần)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.hinaka.pokedex.data.network.retrofit

import dev.hinaka.pokedex.data.network.api.GrowthRateApi
import dev.hinaka.pokedex.data.network.datasource.GrowthRatesNetworkSource
import dev.hinaka.pokedex.data.network.model.NetworkGrowthRate
import dev.hinaka.pokedex.data.network.response.common.id
import dev.hinaka.pokedex.data.network.response.common.isEn
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

internal class RetrofitGrowthRateNetworkSource @Inject constructor(
    private val growthRateApi: GrowthRateApi
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
            maxExp = response.levels?.first { it.level == 100 }?.experience
        )
    }
}

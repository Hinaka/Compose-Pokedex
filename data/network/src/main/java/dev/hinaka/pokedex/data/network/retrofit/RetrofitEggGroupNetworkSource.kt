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

import dev.hinaka.pokedex.data.network.api.EggGroupApi
import dev.hinaka.pokedex.data.network.datasource.EggGroupNetworkSource
import dev.hinaka.pokedex.data.network.model.NetworkEggGroup
import dev.hinaka.pokedex.data.network.response.common.id
import dev.hinaka.pokedex.data.network.response.common.isEn
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

internal class RetrofitEggGroupNetworkSource @Inject constructor(
    private val eggGroupApi: EggGroupApi
) : EggGroupNetworkSource {

    override suspend fun getEggGroups(): List<NetworkEggGroup> = coroutineScope {
        val ids = eggGroupApi.getAllEggGroups().results.orEmpty().mapNotNull { it.id }

        ids.map {
            async { getEggGroup(it) }
        }.awaitAll()
    }

    private suspend fun getEggGroup(id: Int): NetworkEggGroup {
        val response = eggGroupApi.getEggGroup(id)

        return NetworkEggGroup(
            id = response.id,
            name = response.names?.firstOrNull { it.language.isEn }?.name
        )
    }
}

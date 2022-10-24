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

import dev.hinaka.pokedex.data.network.api.NatureApi
import dev.hinaka.pokedex.data.network.datasource.NatureNetworkSource
import dev.hinaka.pokedex.data.network.model.NetworkNature
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

internal class RetrofitNatureNetworkSource @Inject constructor(
    private val natureApi: NatureApi
) : NatureNetworkSource {
    override suspend fun getNatures(offset: Int, limit: Int): List<NetworkNature> =
        coroutineScope {
            val ids = natureApi.getNatures(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { natureApi.getNature(it) }
            }.awaitAll()
        }
}

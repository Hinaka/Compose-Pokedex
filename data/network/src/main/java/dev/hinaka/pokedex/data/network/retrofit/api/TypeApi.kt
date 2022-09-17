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
package dev.hinaka.pokedex.data.network.retrofit.api

import dev.hinaka.pokedex.data.network.model.NetworkPagedResponse
import dev.hinaka.pokedex.data.network.model.NetworkType
import dev.hinaka.pokedex.data.network.model.common.NetworkListResult
import retrofit2.http.GET
import retrofit2.http.Path

interface TypeApi {

    @GET("type")
    suspend fun getTypes(): NetworkListResult

    @GET("type/{id}")
    suspend fun getType(@Path("id") id: Int): NetworkType
}

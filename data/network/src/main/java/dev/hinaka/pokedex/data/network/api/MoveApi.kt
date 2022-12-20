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
package dev.hinaka.pokedex.data.network.api

import dev.hinaka.pokedex.data.network.model.NetworkMove
import dev.hinaka.pokedex.data.network.model.NetworkPagedResponse
import dev.hinaka.pokedex.data.network.response.move.GetMoveResponse
import dev.hinaka.pokedex.data.network.response.move.GetMovesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MoveApi {

    @GET("move")
    suspend fun getMoves(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): GetMovesResponse

    @GET("move/{id}")
    suspend fun getMove(@Path("id") id: Int): GetMoveResponse
}

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
package dev.hinaka.pokedex.feature.move.usecase

import androidx.paging.PagingData
import dev.hinaka.pokedex.data.repository.MoveRepository
import dev.hinaka.pokedex.domain.move.Move
import kotlinx.coroutines.flow.Flow

typealias GetMovePagingUseCase =
    @JvmSuppressWildcards suspend (pageSize: Int) -> Flow<PagingData<Move>>

fun getMovePaging(
    repository: MoveRepository,
    pageSize: Int
) = repository.getMovePagingStream(pageSize)

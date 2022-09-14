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
package dev.hinaka.pokedex.feature.location.usecase

import androidx.paging.PagingData
import dev.hinaka.pokedex.data.repository.LocationRepository
import dev.hinaka.pokedex.domain.Location
import kotlinx.coroutines.flow.Flow

typealias GetLocationPagingUseCase =
    @JvmSuppressWildcards (pageSize: Int) -> Flow<PagingData<Location>>

fun getLocationPaging(
    repository: LocationRepository,
    pageSize: Int
) = repository.getLocationPagingStream(pageSize)

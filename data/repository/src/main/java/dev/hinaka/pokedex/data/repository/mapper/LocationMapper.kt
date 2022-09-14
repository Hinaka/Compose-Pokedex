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
package dev.hinaka.pokedex.data.repository.mapper

import dev.hinaka.pokedex.data.database.model.LocationEntity
import dev.hinaka.pokedex.data.network.model.NetworkLocation
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Location

fun LocationEntity.toDomain() = Location(
    id = Id(id),
    name = name.orEmpty(),
    region = region.orEmpty()
)

fun NetworkLocation.toEntity() = LocationEntity(
    id = id ?: -1,
    name = name,
    region = regionName
)

fun List<LocationEntity>.toDomain() = map { it.toDomain() }

fun List<NetworkLocation>.toEntity() = map { it.toEntity() }

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
package dev.hinaka.pokedex.data.repository

import dev.hinaka.pokedex.data.database.PokedexDatabase
import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.repository.mapper.toEntity
import javax.inject.Inject

class OfflineFirstTypeRepository @Inject constructor(
    private val db: PokedexDatabase,
    private val networkDataSource: PokedexNetworkDataSource
) : TypeRepository {

    private val typeDao = db.typeDao()

    override suspend fun getTypes() {
        val networkTypes = networkDataSource.getTypes()
        typeDao.insertAll(networkTypes.toEntity())
    }
}

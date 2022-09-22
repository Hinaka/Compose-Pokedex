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
package dev.hinaka.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.hinaka.pokedex.data.database.model.type.DamageTypeWithDamageFactor
import dev.hinaka.pokedex.data.database.model.type.TypeDamageRelationEntity
import dev.hinaka.pokedex.data.database.model.type.TypeEntity

@Dao
interface TypeDao {

    @Transaction
    @Query("SELECT * FROM type_damage_relations WHERE target_type_id = :typeId")
    suspend fun loadDamageTakenRelationsOf(typeId: Int): List<DamageTypeWithDamageFactor>

    @Query("SELECT * FROM types")
    suspend fun loadAll(): List<TypeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(typeEntities: List<TypeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTypeDamageRelation(items: List<TypeDamageRelationEntity>)

    @Query("DELETE FROM types")
    suspend fun clearAll()
}

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

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import dev.hinaka.pokedex.data.database.model.pokemon.PokemonDetails
import dev.hinaka.pokedex.data.database.model.pokemon.PokemonEntity
import dev.hinaka.pokedex.data.database.model.pokemon.PokemonWithTypes
import dev.hinaka.pokedex.data.database.model.xref.PokemonEggGroupXRef
import dev.hinaka.pokedex.data.database.model.xref.PokemonGrowthRateXRef
import dev.hinaka.pokedex.data.database.model.xref.PokemonMoveXRef
import dev.hinaka.pokedex.data.database.model.xref.PokemonTypeXRef
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Transaction
    @Query("SELECT * FROM pokemons WHERE paged ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, PokemonWithTypes>

    @Transaction
    @Query("SELECT * FROM pokemons WHERE id = :id")
    fun pokemonDetailsStream(id: Int): Flow<PokemonDetails>

    @Transaction
    @Query(
        """
        SELECT * FROM pokemons
        WHERE id < :id
        ORDER BY id DESC
        LIMIT 1
    """
    )
    fun previousPokemonDetailsOfStream(id: Int): Flow<PokemonDetails?>

    @Transaction
    @Query(
        """
        SELECT * FROM pokemons
        WHERE id > :id
        ORDER BY id ASC
        LIMIT 1
    """
    )
    fun nextPokemonDetailsOfStream(id: Int): Flow<PokemonDetails?>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(pokemonEntities: List<PokemonEntity>)

    @Insert(onConflict = IGNORE)
    suspend fun insertAllTypeXRefs(pokemonTypeXRefs: List<PokemonTypeXRef>)

    @Insert(onConflict = IGNORE)
    suspend fun insertAllMoveXRefs(pokemonMoveXRefs: List<PokemonMoveXRef>)

    @Insert(onConflict = IGNORE)
    suspend fun insertAllEggGroupXRefs(pokemonEggGroupXRefs: List<PokemonEggGroupXRef>)

    @Insert(onConflict = IGNORE)
    suspend fun insertAllGrowthRateXRefs(pokemonGrowthRateXRefs: List<PokemonGrowthRateXRef>)

    @Query("DELETE FROM pokemons")
    suspend fun clearAll()
}

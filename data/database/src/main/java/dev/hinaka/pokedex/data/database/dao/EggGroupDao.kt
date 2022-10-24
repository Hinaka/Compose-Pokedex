package dev.hinaka.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.hinaka.pokedex.data.database.model.pokemon.EggGroupEntity

@Dao
interface EggGroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<EggGroupEntity>)
}
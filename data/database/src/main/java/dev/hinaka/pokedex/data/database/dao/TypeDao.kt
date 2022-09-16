package dev.hinaka.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.hinaka.pokedex.data.database.model.TypeEntity

@Dao
interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(typeEntities: List<TypeEntity>)

    @Query("DELETE FROM types")
    suspend fun clearAll()
}
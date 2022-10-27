package dev.hinaka.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import dev.hinaka.pokedex.data.database.model.growthrate.GrowthRateEntity

@Dao
interface GrowthRateDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(entities: List<GrowthRateEntity>)
}
package dev.hinaka.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.hinaka.pokedex.data.database.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query(value = "SELECT * FROM pokemons")
    fun getPokemonEntitiesStream(): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplacesPokemons(pokemonEntities: List<PokemonEntity>)
}
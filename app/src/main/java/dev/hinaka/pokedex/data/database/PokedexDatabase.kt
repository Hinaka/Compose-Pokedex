package dev.hinaka.pokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.hinaka.pokedex.data.database.dao.PokemonDao
import dev.hinaka.pokedex.data.database.model.PokemonEntity

@Database(
    version = 1,
    entities = [PokemonEntity::class],
)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
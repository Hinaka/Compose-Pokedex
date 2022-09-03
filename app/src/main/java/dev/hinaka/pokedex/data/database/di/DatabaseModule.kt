package dev.hinaka.pokedex.data.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.database.PokedexDatabase
import dev.hinaka.pokedex.data.database.dao.PokemonDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesPokedexDatabase(
        @ApplicationContext context: Context,
    ): PokedexDatabase = Room.databaseBuilder(
        context,
        PokedexDatabase::class.java,
        "pokedex-database"
    ).build()

    @Provides
    fun providesPokemonDao(db: PokedexDatabase): PokemonDao = db.pokemonDao()
}
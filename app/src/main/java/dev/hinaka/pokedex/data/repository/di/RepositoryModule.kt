package dev.hinaka.pokedex.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.repository.PokemonDataRepository
import dev.hinaka.pokedex.data.repository.PokemonRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun binds(repository: PokemonDataRepository): PokemonRepository
}
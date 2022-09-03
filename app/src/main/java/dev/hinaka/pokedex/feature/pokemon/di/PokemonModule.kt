package dev.hinaka.pokedex.feature.pokemon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.feature.pokemon.usecase.GetPokemonsUseCase
import dev.hinaka.pokedex.feature.pokemon.usecase.getPokemons

@Module
@InstallIn(SingletonComponent::class)
object PokemonModule {

    @Provides
    fun providesGetPokemonsUseCase(): GetPokemonsUseCase {
        return {
            getPokemons()
        }
    }
}
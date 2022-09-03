package dev.hinaka.pokedex.data.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.network.retrofit.RetrofitPokedexNetworkDataSource

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsPokedexNetworkDataSource(
        dataSource: RetrofitPokedexNetworkDataSource,
    ): PokedexNetworkDataSource
}
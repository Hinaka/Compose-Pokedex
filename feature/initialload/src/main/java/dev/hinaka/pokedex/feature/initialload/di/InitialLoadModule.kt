package dev.hinaka.pokedex.feature.initialload.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.repository.TypeRepository
import dev.hinaka.pokedex.feature.initialload.usecase.SyncTypesUseCase
import dev.hinaka.pokedex.feature.initialload.usecase.syncTypes

@Module
@InstallIn(SingletonComponent::class)
object InitialLoadModule {

    @Provides
    fun providesSyncTypesUseCase(repository: TypeRepository): SyncTypesUseCase {
        return {
            syncTypes(repository)
        }
    }
}
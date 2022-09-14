package dev.hinaka.pokedex.feature.location.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.repository.LocationRepository
import dev.hinaka.pokedex.feature.location.usecase.GetLocationPagingUseCase

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    fun providesGetLocationPagingUseCase(repository: LocationRepository): GetLocationPagingUseCase {
        return { pageSize -> repository.getLocationPagingStream(pageSize) }
    }
}
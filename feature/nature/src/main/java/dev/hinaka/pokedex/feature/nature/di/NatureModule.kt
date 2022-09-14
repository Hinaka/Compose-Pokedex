package dev.hinaka.pokedex.feature.nature.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.repository.NatureRepository
import dev.hinaka.pokedex.feature.nature.usecase.GetNaturePagingUseCase
import dev.hinaka.pokedex.feature.nature.usecase.getNaturePaging

@Module
@InstallIn(SingletonComponent::class)
object NatureModule {

    @Provides
    fun providesGetNaturePagingUseCase(repository: NatureRepository): GetNaturePagingUseCase {
        return { pageSize ->
            getNaturePaging(repository, pageSize)
        }
    }
}
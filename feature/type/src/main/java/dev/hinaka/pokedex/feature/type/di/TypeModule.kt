package dev.hinaka.pokedex.feature.type.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.repository.TypeRepository
import dev.hinaka.pokedex.feature.type.usecase.GetTypeDamageTakenRelationsUseCase
import dev.hinaka.pokedex.feature.type.usecase.getTypeDamageTakenRelations

@Module
@InstallIn(SingletonComponent::class)
object TypeModule {

    @Provides
    fun providesGetTypeDamageTakenRelationsUseCase(
        repository: TypeRepository
    ): GetTypeDamageTakenRelationsUseCase {
        return {
            getTypeDamageTakenRelations(repository, it)
        }
    }
}
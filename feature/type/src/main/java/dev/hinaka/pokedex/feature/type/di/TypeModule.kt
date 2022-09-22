package dev.hinaka.pokedex.feature.type.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.repository.TypeRepository
import dev.hinaka.pokedex.feature.type.usecase.GetDamageRelationsOfTypeUseCase
import dev.hinaka.pokedex.feature.type.usecase.getDamageRelationsOfType

@Module
@InstallIn(SingletonComponent::class)
object TypeModule {

    @Provides
    fun providesGetDamageRelationOfTypesUseCase(
        repository: TypeRepository
    ): GetDamageRelationsOfTypeUseCase {
        return {
            getDamageRelationsOfType(repository, it)
        }
    }
}
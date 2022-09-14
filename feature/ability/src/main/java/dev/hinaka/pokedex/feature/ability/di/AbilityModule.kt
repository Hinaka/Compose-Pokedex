package dev.hinaka.pokedex.feature.ability.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.repository.AbilityRepository
import dev.hinaka.pokedex.feature.ability.usecase.GetAbilityPagingUseCase
import dev.hinaka.pokedex.feature.ability.usecase.getAbilityPaging

@Module
@InstallIn(SingletonComponent::class)
object AbilityModule {

    @Provides
    fun providesGetAbilityPagingUseCase(repository: AbilityRepository): GetAbilityPagingUseCase {
        return { pageSize ->
            getAbilityPaging(repository, pageSize)
        }
    }
}
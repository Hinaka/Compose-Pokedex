package dev.hinaka.pokedex.feature.move.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.repository.MoveRepository
import dev.hinaka.pokedex.feature.move.usecase.GetMovePagingUseCase
import dev.hinaka.pokedex.feature.move.usecase.getMovePaging

@Module
@InstallIn(SingletonComponent::class)
object MoveModule {

    @Provides
    fun providesGetMovePagingUseCase(repository: MoveRepository): GetMovePagingUseCase {
        return { pageSize ->
            getMovePaging(repository, pageSize)
        }
    }
}
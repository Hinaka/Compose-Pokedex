package dev.hinaka.pokedex.feature.item.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.repository.ItemRepository
import dev.hinaka.pokedex.feature.item.usecase.GetItemPagingUseCase
import dev.hinaka.pokedex.feature.item.usecase.getItemPaging

@Module
@InstallIn(SingletonComponent::class)
object ItemModule {

    @Provides
    fun providesGetItemPagingUseCase(repository: ItemRepository): GetItemPagingUseCase {
        return { pageSize ->
            getItemPaging(repository, pageSize)
        }
    }
}
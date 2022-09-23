/*
 * Copyright 2022 Hinaka (Trung Nguyễn Minh Trần)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.hinaka.pokedex.feature.type.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.repository.TypeRepository
import dev.hinaka.pokedex.feature.type.usecase.GetAllTypesStreamUseCase
import dev.hinaka.pokedex.feature.type.usecase.GetTypeDamageTakenRelationsStreamUseCase
import dev.hinaka.pokedex.feature.type.usecase.getAllTypes
import dev.hinaka.pokedex.feature.type.usecase.getTypeDamageTakenRelations

@Module
@InstallIn(SingletonComponent::class)
object TypeModule {

    @Provides
    fun providesGetTypeDamageTakenRelationsStreamUseCase(
        repository: TypeRepository
    ): GetTypeDamageTakenRelationsStreamUseCase {
        return {
            getTypeDamageTakenRelations(repository, it)
        }
    }

    @Provides
    fun providesGetAllTypesStreamUseCase(repository: TypeRepository): GetAllTypesStreamUseCase {
        return {
            getAllTypes(repository)
        }
    }
}

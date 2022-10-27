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
package dev.hinaka.pokedex.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.repository.AbilityRepository
import dev.hinaka.pokedex.data.repository.EggGroupRepository
import dev.hinaka.pokedex.data.repository.GrowthRateRepository
import dev.hinaka.pokedex.data.repository.ItemRepository
import dev.hinaka.pokedex.data.repository.LocationRepository
import dev.hinaka.pokedex.data.repository.MoveRepository
import dev.hinaka.pokedex.data.repository.NatureRepository
import dev.hinaka.pokedex.data.repository.OfflineFirstAbilityRepository
import dev.hinaka.pokedex.data.repository.OfflineFirstEggGroupRepository
import dev.hinaka.pokedex.data.repository.OfflineFirstGrowthRatesRepository
import dev.hinaka.pokedex.data.repository.OfflineFirstItemRepository
import dev.hinaka.pokedex.data.repository.OfflineFirstLocationRepository
import dev.hinaka.pokedex.data.repository.OfflineFirstMoveRepository
import dev.hinaka.pokedex.data.repository.OfflineFirstNatureRepository
import dev.hinaka.pokedex.data.repository.OfflineFirstPokemonRepository
import dev.hinaka.pokedex.data.repository.OfflineFirstTypeRepository
import dev.hinaka.pokedex.data.repository.PokemonRepository
import dev.hinaka.pokedex.data.repository.TypeRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsPokemonRepository(repository: OfflineFirstPokemonRepository): PokemonRepository

    @Binds
    fun bindsItemRepository(repository: OfflineFirstItemRepository): ItemRepository

    @Binds
    fun bindsMoveRepository(repository: OfflineFirstMoveRepository): MoveRepository

    @Binds
    fun bindsLocationRepository(repository: OfflineFirstLocationRepository): LocationRepository

    @Binds
    fun bindsAbilityRepository(repository: OfflineFirstAbilityRepository): AbilityRepository

    @Binds
    fun bindsNatureRepository(repository: OfflineFirstNatureRepository): NatureRepository

    @Binds
    fun bindsTypeRepository(repository: OfflineFirstTypeRepository): TypeRepository

    @Binds
    fun bindsEggGroupRepository(repository: OfflineFirstEggGroupRepository): EggGroupRepository

    @Binds
    fun bindsGrowthRateRepository(
        repository: OfflineFirstGrowthRatesRepository
    ): GrowthRateRepository
}

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
package dev.hinaka.pokedex.data.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.network.PokedexNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.RetrofitPokedexNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.source.RetrofitAbilityNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.source.RetrofitItemNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.source.RetrofitLocationNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.source.RetrofitMoveNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.source.RetrofitNatureNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.source.RetrofitPokemonNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.source.RetrofitTypeNetworkSource
import dev.hinaka.pokedex.data.network.source.AbilityNetworkSource
import dev.hinaka.pokedex.data.network.source.ItemNetworkSource
import dev.hinaka.pokedex.data.network.source.LocationNetworkSource
import dev.hinaka.pokedex.data.network.source.MoveNetworkSource
import dev.hinaka.pokedex.data.network.source.NatureNetworkSource
import dev.hinaka.pokedex.data.network.source.PokemonNetworkSource
import dev.hinaka.pokedex.data.network.source.TypeNetworkSource

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsPokemonNetworkSource(
        source: RetrofitPokemonNetworkSource
    ): PokemonNetworkSource

    @Binds
    fun bindsItemNetworkSource(
        source: RetrofitItemNetworkSource
    ): ItemNetworkSource

    @Binds
    fun bindsMoveNetworkSource(
        source: RetrofitMoveNetworkSource
    ): MoveNetworkSource

    @Binds
    fun bindsAbilityNetworkSource(
        source: RetrofitAbilityNetworkSource
    ): AbilityNetworkSource

    @Binds
    fun bindsLocationNetworkSource(
        source: RetrofitLocationNetworkSource
    ): LocationNetworkSource

    @Binds
    fun bindsNatureNetworkSource(
        source: RetrofitNatureNetworkSource
    ): NatureNetworkSource

    @Binds
    fun bindsTypeNetworkSource(
        source: RetrofitTypeNetworkSource
    ): TypeNetworkSource

    @Binds
    fun bindsPokedexNetworkSource(
        source: RetrofitPokedexNetworkSource
    ): PokedexNetworkSource
}

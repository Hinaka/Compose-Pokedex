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
package dev.hinaka.pokedex.data.network.retrofit.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.network.retrofit.api.AbilityApi
import dev.hinaka.pokedex.data.network.retrofit.api.ItemApi
import dev.hinaka.pokedex.data.network.retrofit.api.LocationApi
import dev.hinaka.pokedex.data.network.retrofit.api.MoveApi
import dev.hinaka.pokedex.data.network.retrofit.api.NatureApi
import dev.hinaka.pokedex.data.network.retrofit.api.PokemonApi
import dev.hinaka.pokedex.data.network.retrofit.api.TypeApi
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        @ApplicationContext context: Context
    ): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .build()
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun providesPokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)

    @Provides
    fun providesItemApi(retrofit: Retrofit): ItemApi = retrofit.create(ItemApi::class.java)

    @Provides
    fun providesMoveApi(retrofit: Retrofit): MoveApi = retrofit.create(MoveApi::class.java)

    @Provides
    fun providesLocationApi(retrofit: Retrofit): LocationApi =
        retrofit.create(LocationApi::class.java)

    @Provides
    fun providesAbilityApi(retrofit: Retrofit): AbilityApi = retrofit.create(AbilityApi::class.java)

    @Provides
    fun providesNatureApi(retrofit: Retrofit): NatureApi = retrofit.create(NatureApi::class.java)

    @Provides
    fun providesTypeApi(retrofit: Retrofit): TypeApi = retrofit.create(TypeApi::class.java)
}

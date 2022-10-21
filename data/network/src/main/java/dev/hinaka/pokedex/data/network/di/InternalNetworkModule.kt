package dev.hinaka.pokedex.data.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.hinaka.pokedex.data.network.api.AbilityApi
import dev.hinaka.pokedex.data.network.api.ItemApi
import dev.hinaka.pokedex.data.network.api.LocationApi
import dev.hinaka.pokedex.data.network.api.MoveApi
import dev.hinaka.pokedex.data.network.api.NatureApi
import dev.hinaka.pokedex.data.network.api.PokemonApi
import dev.hinaka.pokedex.data.network.api.TypeApi
import dev.hinaka.pokedex.data.network.datasource.AbilityNetworkSource
import dev.hinaka.pokedex.data.network.datasource.ItemNetworkSource
import dev.hinaka.pokedex.data.network.datasource.LocationNetworkSource
import dev.hinaka.pokedex.data.network.datasource.MoveNetworkSource
import dev.hinaka.pokedex.data.network.datasource.NatureNetworkSource
import dev.hinaka.pokedex.data.network.datasource.PokedexNetworkSource
import dev.hinaka.pokedex.data.network.datasource.PokemonNetworkSource
import dev.hinaka.pokedex.data.network.datasource.TypeNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.RetrofitAbilityNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.RetrofitItemNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.RetrofitLocationNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.RetrofitMoveNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.RetrofitNatureNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.RetrofitPokedexNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.RetrofitPokemonNetworkSource
import dev.hinaka.pokedex.data.network.retrofit.RetrofitTypeNetworkSource
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalNetworkModule {

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

    companion object {

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
                .retryOnConnectionFailure(true)
                .build()
            return Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .client(client)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
        }

        @Provides
        fun providesPokemonApi(retrofit: Retrofit): PokemonApi =
            retrofit.create(PokemonApi::class.java)

        @Provides
        fun providesItemApi(retrofit: Retrofit): ItemApi = retrofit.create(ItemApi::class.java)

        @Provides
        fun providesMoveApi(retrofit: Retrofit): MoveApi = retrofit.create(MoveApi::class.java)

        @Provides
        fun providesLocationApi(retrofit: Retrofit): LocationApi =
            retrofit.create(LocationApi::class.java)

        @Provides
        fun providesAbilityApi(retrofit: Retrofit): AbilityApi =
            retrofit.create(AbilityApi::class.java)

        @Provides
        fun providesNatureApi(retrofit: Retrofit): NatureApi =
            retrofit.create(NatureApi::class.java)

        @Provides
        fun providesTypeApi(retrofit: Retrofit): TypeApi = retrofit.create(TypeApi::class.java)
    }
}
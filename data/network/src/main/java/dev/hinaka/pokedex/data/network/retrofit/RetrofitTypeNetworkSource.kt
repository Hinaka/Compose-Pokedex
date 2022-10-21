package dev.hinaka.pokedex.data.network.retrofit

import dev.hinaka.pokedex.data.network.api.TypeApi
import dev.hinaka.pokedex.data.network.model.NetworkType
import dev.hinaka.pokedex.data.network.model.common.ids
import dev.hinaka.pokedex.data.network.datasource.TypeNetworkSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class RetrofitTypeNetworkSource @Inject constructor(
    private val typeApi: TypeApi
) : TypeNetworkSource {
    override suspend fun getTypes(): List<NetworkType> =
        coroutineScope {
            val networkListResult = typeApi.getTypes()
            networkListResult.ids.orEmpty().map {
                async { typeApi.getType(it) }
            }.awaitAll()
        }
}
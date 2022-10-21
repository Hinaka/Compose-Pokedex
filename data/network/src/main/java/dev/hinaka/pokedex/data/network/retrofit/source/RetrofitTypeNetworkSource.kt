package dev.hinaka.pokedex.data.network.retrofit.source

import dev.hinaka.pokedex.data.network.model.NetworkType
import dev.hinaka.pokedex.data.network.model.common.ids
import dev.hinaka.pokedex.data.network.retrofit.api.TypeApi
import dev.hinaka.pokedex.data.network.source.TypeNetworkSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class RetrofitTypeNetworkSource @Inject constructor(
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
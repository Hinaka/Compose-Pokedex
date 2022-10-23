package dev.hinaka.pokedex.data.network.retrofit

import dev.hinaka.pokedex.data.network.api.NatureApi
import dev.hinaka.pokedex.data.network.model.NetworkNature
import dev.hinaka.pokedex.data.network.datasource.NatureNetworkSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class RetrofitNatureNetworkSource @Inject constructor(
    private val natureApi: NatureApi
) : NatureNetworkSource {
    override suspend fun getNatures(offset: Int, limit: Int): List<NetworkNature> =
        coroutineScope {
            val ids = natureApi.getNatures(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { natureApi.getNature(it) }
            }.awaitAll()
        }
}
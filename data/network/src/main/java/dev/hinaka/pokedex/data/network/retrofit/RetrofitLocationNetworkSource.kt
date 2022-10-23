package dev.hinaka.pokedex.data.network.retrofit

import dev.hinaka.pokedex.data.network.api.LocationApi
import dev.hinaka.pokedex.data.network.model.NetworkLocation
import dev.hinaka.pokedex.data.network.datasource.LocationNetworkSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class RetrofitLocationNetworkSource @Inject constructor(
    private val locationApi: LocationApi,
) : LocationNetworkSource {
    override suspend fun getLocations(offset: Int, limit: Int): List<NetworkLocation> =
        coroutineScope {
            val ids = locationApi.getLocations(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { locationApi.getLocation(it) }
            }.awaitAll()
        }
}
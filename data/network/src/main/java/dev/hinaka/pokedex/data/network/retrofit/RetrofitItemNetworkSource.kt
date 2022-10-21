package dev.hinaka.pokedex.data.network.retrofit

import dev.hinaka.pokedex.data.network.api.ItemApi
import dev.hinaka.pokedex.data.network.model.NetworkItem
import dev.hinaka.pokedex.data.network.datasource.ItemNetworkSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class RetrofitItemNetworkSource @Inject constructor(
    private val itemApi: ItemApi,
) : ItemNetworkSource {
    override suspend fun getItems(offset: Int, limit: Int): List<NetworkItem> =
        coroutineScope {
            val ids = itemApi.getItems(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { itemApi.getItem(it) }
            }.awaitAll()
        }
}
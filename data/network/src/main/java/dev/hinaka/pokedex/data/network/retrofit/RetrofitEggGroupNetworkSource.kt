package dev.hinaka.pokedex.data.network.retrofit

import dev.hinaka.pokedex.data.network.api.EggGroupApi
import dev.hinaka.pokedex.data.network.datasource.EggGroupNetworkSource
import dev.hinaka.pokedex.data.network.model.NetworkEggGroup
import dev.hinaka.pokedex.data.network.response.common.id
import dev.hinaka.pokedex.data.network.response.common.isEn
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class RetrofitEggGroupNetworkSource @Inject constructor(
    private val eggGroupApi: EggGroupApi,
) : EggGroupNetworkSource {

    override suspend fun getEggGroups(): List<NetworkEggGroup> = coroutineScope {
        val ids = eggGroupApi.getAllEggGroups().results.orEmpty().mapNotNull { it.id }

        ids.map {
            async { getEggGroup(it) }
        }.awaitAll()
    }

    private suspend fun getEggGroup(id: Int): NetworkEggGroup {
        val response = eggGroupApi.getEggGroup(id)

        return NetworkEggGroup(
            id = response.id,
            name = response.names?.firstOrNull { it.language.isEn }?.name
        )
    }
}
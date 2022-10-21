package dev.hinaka.pokedex.data.network.retrofit.source

import dev.hinaka.pokedex.data.network.model.NetworkAbility
import dev.hinaka.pokedex.data.network.retrofit.api.AbilityApi
import dev.hinaka.pokedex.data.network.source.AbilityNetworkSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class RetrofitAbilityNetworkSource @Inject constructor(
    private val abilityApi: AbilityApi,
) : AbilityNetworkSource {
    override suspend fun getAbilities(offset: Int, limit: Int): List<NetworkAbility> =
        coroutineScope {
            val ids = abilityApi.getAbilities(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { abilityApi.getAbility(it) }
            }.awaitAll()
        }

    override suspend fun getAbilities(ids: List<Int>): List<NetworkAbility> =
        coroutineScope {
            ids.map {
                async { abilityApi.getAbility(it) }
            }.awaitAll()
        }
}
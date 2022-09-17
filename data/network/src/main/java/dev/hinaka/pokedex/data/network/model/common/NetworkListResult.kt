package dev.hinaka.pokedex.data.network.model.common

import kotlinx.serialization.Serializable

@Serializable
data class NetworkListResult(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<NetworkListItem>?
)

val NetworkListResult.ids get() = results?.mapNotNull { it.id }

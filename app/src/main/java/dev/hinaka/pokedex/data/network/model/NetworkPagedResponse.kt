package dev.hinaka.pokedex.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPagedResponse(
    val results: List<NetworkPagedItem>?,
) {
    @Serializable
    data class NetworkPagedItem(
        val name: String?,
        val url: String?,
    ) {
        val id get() = url.orEmpty().split("/").last { it.isNotEmpty() }.toIntOrNull()
    }
}

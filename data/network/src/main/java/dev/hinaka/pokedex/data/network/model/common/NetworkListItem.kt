package dev.hinaka.pokedex.data.network.model.common

import kotlinx.serialization.Serializable

@Serializable
data class NetworkListItem(
    val name: String?,
    val url: String?,
)

val NetworkListItem.id
    get() = url.orEmpty()
        .split("/")
        .last { it.isNotEmpty() }
        .toIntOrNull()

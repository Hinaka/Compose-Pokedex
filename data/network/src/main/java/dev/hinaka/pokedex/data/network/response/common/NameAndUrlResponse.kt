package dev.hinaka.pokedex.data.network.response.common

import kotlinx.serialization.Serializable

@Serializable
data class NameAndUrlResponse(
    val name: String?,
    val url: String?,
)

val NameAndUrlResponse.id
    get() = url.orEmpty()
        .split("/")
        .last { it.isNotEmpty() }
        .toIntOrNull()

package dev.hinaka.pokedex.data.network.response.common

import kotlinx.serialization.Serializable

@Serializable
data class GetPagingResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<NameAndUrlResponse>?
)

val GetPagingResponse.ids get() = results.orEmpty().mapNotNull { it.id }
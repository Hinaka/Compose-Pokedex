package dev.hinaka.pokedex.data.network.model.common

import kotlinx.serialization.Serializable

@Serializable
data class NetworkName(
    val name: String?,
    val language: NetworkLanguage?,
)

val List<NetworkName>?.enName get() = this?.first { it.language.isEn }?.name
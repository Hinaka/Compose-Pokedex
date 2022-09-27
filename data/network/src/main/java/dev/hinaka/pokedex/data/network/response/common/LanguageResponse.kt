package dev.hinaka.pokedex.data.network.response.common

import kotlinx.serialization.Serializable

@Serializable
data class LanguageResponse(
    val name: String?,
)

val LanguageResponse?.isEn get() = this?.name == "en"

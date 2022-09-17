package dev.hinaka.pokedex.data.network.model.common

import kotlinx.serialization.Serializable

@Serializable
data class NetworkLanguage(
    val name: String?
)

val NetworkLanguage?.isEn get() = this?.name == "en"
package dev.hinaka.pokedex.data.network.model

@kotlinx.serialization.Serializable
data class NetworkGrowthRate(
    val id: Int?,
    val description: String?,
    val maxExp: Int?
)

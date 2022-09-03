package dev.hinaka.pokedex.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPokemon(
    val id: Int?,
    val name: String?,
)

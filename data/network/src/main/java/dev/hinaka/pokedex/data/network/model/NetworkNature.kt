package dev.hinaka.pokedex.data.network.model

import dev.hinaka.pokedex.data.network.model.NetworkItem.Name
import kotlinx.serialization.Serializable

@Serializable
data class NetworkNature(
    val id: Int?,
    val names: List<Name>?,
) {
    val name get() = names?.first { it.language?.name == "en" }?.name.orEmpty()
}
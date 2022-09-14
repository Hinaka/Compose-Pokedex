package dev.hinaka.pokedex.data.network.model

import dev.hinaka.pokedex.data.network.model.NetworkItem.Name
import dev.hinaka.pokedex.data.network.model.NetworkPagedResponse.NetworkPagedItem
import kotlinx.serialization.Serializable

@Serializable
data class NetworkLocation(
    val id: Int?,
    val names: List<Name>?,
    val region: NetworkPagedItem?,
) {
    val name get() = names?.first { it.language?.name == "en" }?.name.orEmpty()
    val regionName get() = region?.name.orEmpty()
}

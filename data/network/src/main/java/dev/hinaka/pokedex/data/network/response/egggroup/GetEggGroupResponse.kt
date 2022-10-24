package dev.hinaka.pokedex.data.network.response.egggroup

import dev.hinaka.pokedex.data.network.response.common.LanguageResponse
import kotlinx.serialization.Serializable

@Serializable
internal data class GetEggGroupResponse(
    val id: Int?,
    val names: List<Name>?
) {
    @Serializable
    data class Name(
        val language: LanguageResponse?,
        val name: String?
    )
}

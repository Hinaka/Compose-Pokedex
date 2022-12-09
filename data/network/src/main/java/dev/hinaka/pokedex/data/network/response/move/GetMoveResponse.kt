package dev.hinaka.pokedex.data.network.response.move

import dev.hinaka.pokedex.data.network.response.common.LanguageResponse
import dev.hinaka.pokedex.data.network.response.common.NameAndUrlResponse
import kotlinx.serialization.Serializable

@Serializable
internal data class GetMoveResponse(
    val id: Int?,
    val names: List<Name>?,
    val type: NameAndUrlResponse?,
    val damage_class: NameAndUrlResponse?,
    val accuracy: Int?,
    val power: Int?,
    val pp: Int?,
) {

    @Serializable
    data class Name(
        val language: LanguageResponse?,
        val name: String?
    )
}
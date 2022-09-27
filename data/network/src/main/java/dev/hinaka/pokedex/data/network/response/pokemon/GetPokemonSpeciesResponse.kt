package dev.hinaka.pokedex.data.network.response.pokemon

import dev.hinaka.pokedex.data.network.response.common.LanguageResponse
import kotlinx.serialization.Serializable

@Serializable
data class GetPokemonSpeciesResponse(
    val names: List<Name>?,
    val genera: List<Genus>?,
    val flavor_text_entries: List<FlavorText>?
) {

    @Serializable
    data class Name(
        val language: LanguageResponse?,
        val name: String?,
    )

    @Serializable
    data class Genus(
        val language: LanguageResponse?,
        val genus: String?,
    )

    @Serializable
    data class FlavorText(
        val language: LanguageResponse?,
        val flavor_text: String?
    )
}
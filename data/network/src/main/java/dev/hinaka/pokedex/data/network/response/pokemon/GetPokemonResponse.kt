package dev.hinaka.pokedex.data.network.response.pokemon

import dev.hinaka.pokedex.data.network.response.common.NameAndUrlResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPokemonResponse(
    val id: Int?,
    val height: Int?,
    val weight: Int?,
    val types: List<Type>?,
    val abilities: List<Ability>?,
    val moves: List<Move>?,
    val species: NameAndUrlResponse?,
    val stats: List<Stat>?,
    val sprites: Sprites?,
    val name: String?,
) {

    @Serializable
    data class Type(
        val type: NameAndUrlResponse?,
        val slot: Int?,
    )

    @Serializable
    data class Ability(
        val ability: NameAndUrlResponse?,
        val is_hidden: Boolean?,
    )

    @Serializable
    data class Move(
        val move: NameAndUrlResponse?,
    )

    @Serializable
    data class Stat(
        val stat: NameAndUrlResponse?,
        val base_stat: Int?,
    )

    @Serializable
    data class Sprites(
        val back_default: String?,
        val back_shiny: String?,
        val front_default: String?,
        val front_shiny: String?,
        val other: Other?,
    ) {

        @Serializable
        data class Other(
            @SerialName("official-artwork") val officialArtwork: OfficialArtwork?,
        ) {

            @Serializable
            data class OfficialArtwork(
                val front_default: String?,
            )
        }
    }
}

package dev.hinaka.pokedex.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkItem(
    val id: Int?,
    val names: List<Name>?,
    val effect_entries: List<EffectEntry>?,
    val sprites: Sprites?,
) {
    @Serializable
    data class EffectEntry(
        val short_effect: String?,
        val language: Language?,
    )

    @Serializable
    data class Language(
        val name: String?,
    )

    @Serializable
    data class Name(
        val language: Language?,
        val name: String?,
    )

    @Serializable
    data class Sprites(
        val default: String?,
    )

    val name get() = names?.first { it.language?.name == "en" }?.name.orEmpty()
    val effect get() = effect_entries?.first { it.language?.name == "en" }?.short_effect.orEmpty()
    val imageUrl get() = sprites?.default.orEmpty()
}
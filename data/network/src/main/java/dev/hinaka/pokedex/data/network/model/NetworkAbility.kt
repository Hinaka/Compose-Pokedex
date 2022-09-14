package dev.hinaka.pokedex.data.network.model

import dev.hinaka.pokedex.data.network.model.NetworkItem.EffectEntry
import dev.hinaka.pokedex.data.network.model.NetworkItem.Name
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAbility(
    val id: Int?,
    val names: List<Name>?,
    val effect_entries: List<EffectEntry>?,
) {
    val name get() = names?.first { it.language?.name == "en" }?.name.orEmpty()
    val effect get() = effect_entries?.first { it.language?.name == "en" }?.short_effect.orEmpty()
}

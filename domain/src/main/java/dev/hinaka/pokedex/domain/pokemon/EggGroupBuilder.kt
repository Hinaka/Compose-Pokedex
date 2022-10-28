package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.common.DomainDslMarker

@DomainDslMarker
class EggGroupBuilder(private val id: Id) {

    var name: String? = null

    fun build(): EggGroup {
        return EggGroup(
            id = id,
            name = name.orEmpty(),
        )
    }
}
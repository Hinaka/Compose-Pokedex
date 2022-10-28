package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.common.DomainBuilder
import dev.hinaka.pokedex.domain.common.build

class EggGroupBuilder(private val id: Int) : DomainBuilder<EggGroup> {

    var name: String? = null

    override fun build(): EggGroup {
        return EggGroup(
            id = Id(id),
            name = name.orEmpty(),
        )
    }
}

fun eggGroup(id: Int, init: EggGroupBuilder.() -> Unit): EggGroup = build(EggGroupBuilder(id), init)
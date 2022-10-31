package dev.hinaka.pokedex.core.ui.utils.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.pokemon.pokemon
import dev.hinaka.pokedex.domain.type.Type

class PokemonProvider : PreviewParameterProvider<Pokemon> {
    override val values: Sequence<Pokemon>
        get() = sequenceOf(
            generatePokemon(1)
        )
}

class PokemonsProvider : PreviewParameterProvider<List<Pokemon>> {
    override val values: Sequence<List<Pokemon>>
        get() = sequenceOf(
            (1..10).map { generatePokemon(it) }
        )
}

private fun generatePokemon(id: Int) = pokemon(id) {
    name = "Pokemon $id"
    genus = "Genus $id"

    val types = Type.Identifier.values()
    val typeIndex = id % types.size
    type(typeIndex) {
        identifier = types[typeIndex]
        name = types[typeIndex].name
    }

    if (id % 2 != 0) {
        val secondaryTypeIndex = (typeIndex + 1) % types.size
        type(secondaryTypeIndex) {
            identifier = types[secondaryTypeIndex]
            name = types[secondaryTypeIndex].name
        }
    }
}

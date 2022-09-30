package dev.hinaka.pokedex.core.ui.utils.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.pokemon.Pokemon
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.domain.type.Type.Identifier.GRASS
import dev.hinaka.pokedex.domain.type.Type.Identifier.POISON

class PokemonPreviewParameterProvider : PreviewParameterProvider<Pokemon> {
    override val values: Sequence<Pokemon>
        get() = sequenceOf(
            Pokemon(
                id = Id(1),
                name = "Bulbasaur",
                types = listOf(
                    Type(
                        id = Id(12),
                        identifier = GRASS,
                        name = "Grass"
                    ),
                    Type(
                        id = Id(4),
                        identifier = POISON,
                        name = "Poison"
                    )
                )
            )
        )
}
package dev.hinaka.pokedex.core.ui.utils.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.hinaka.pokedex.domain.move.DamageClass
import dev.hinaka.pokedex.domain.move.Move
import dev.hinaka.pokedex.domain.move.move
import dev.hinaka.pokedex.domain.type.Type

class MoveProvider : PreviewParameterProvider<Move> {
    override val values: Sequence<Move>
        get() = sequenceOf(
            generateMove(1)
        )
}

private fun generateMove(id: Int) = move(id) {
    name = "Move $id"
    acc = id
    pp = id
    power = id

    val types = Type.Identifier.values()
    val typeIndex = id % types.size
    type(typeIndex) {
        identifier = types[typeIndex]
        name = types[typeIndex].name
    }

    val damageClasses = DamageClass.values()
    val damageClassIndex = id % damageClasses.size
    damageClass = damageClasses[damageClassIndex]
}
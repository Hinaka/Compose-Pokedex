package dev.hinaka.pokedex.domain.move

import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.common.DomainBuilder
import dev.hinaka.pokedex.domain.common.build
import dev.hinaka.pokedex.domain.move.DamageClass.NOTHING
import dev.hinaka.pokedex.domain.pokemon.TypeBuilder
import dev.hinaka.pokedex.domain.type.Type

fun move(id: Int, init: MoveBuilder.() -> Unit): Move = build(MoveBuilder(id), init)

fun learnableMove(id: Int, init: LearnableMoveBuilder.() -> Unit) =
    build(LearnableMoveBuilder(id), init)

class MoveBuilder(private val id: Int) : DomainBuilder<Move> {

    var name: String? = null
    var damageClass: DamageClass? = null
    private var type: Type = TypeBuilder(-1).build()
    var power: Int? = null
    var acc: Int? = null
    var pp: Int? = null

    fun type(id: Int, init: TypeBuilder.() -> Unit) {
        type = build(TypeBuilder(id), init)
    }

    override fun build(): Move {
        return Move(
            id = Id(id),
            name = name.orEmpty(),
            type = type,
            damageClass = damageClass ?: NOTHING,
            power = power ?: 0,
            acc = acc ?: 0,
            pp = pp ?: 0
        )
    }
}

class LearnableMoveBuilder(private val id: Int) : DomainBuilder<LearnableMove> {

    var name: String = ""
    var damageClass: DamageClass = DamageClass.PHYSICAL
    var learnMethod = LearnMethod.LEVEL
    var power = 0
    var acc = 0
    var pp = 0
    private var type: Type = TypeBuilder(-1).build()

    fun type(id: Int, init: TypeBuilder.() -> Unit) {
        type = build(TypeBuilder(id), init)
    }

    override fun build(): LearnableMove {
        return LearnableMove(
            move = Move(
                id = Id(id),
                name = name,
                type = type,
                damageClass = damageClass,
                power = power,
                acc = acc,
                pp = pp
            ),
            learnMethod = learnMethod
        )
    }
}


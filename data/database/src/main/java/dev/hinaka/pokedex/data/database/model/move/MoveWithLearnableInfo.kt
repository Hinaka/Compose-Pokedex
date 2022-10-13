package dev.hinaka.pokedex.data.database.model.move

import androidx.room.ColumnInfo
import androidx.room.Embedded
import dev.hinaka.pokedex.domain.move.LearnMethod
import dev.hinaka.pokedex.domain.move.LearnableMove

data class MoveWithLearnableInfo(
    @Embedded val move: MoveEntity,
    @ColumnInfo(name = "learn_level") val learnLevel: Int?,
    @ColumnInfo(name = "learn_method") val learnMethod: LearnMethod?,
)

fun MoveWithLearnableInfo.toLearnableMove() = learnMethod?.let {
    LearnableMove(
        move = move.toMove(),
        learnMethod = it
    )
}

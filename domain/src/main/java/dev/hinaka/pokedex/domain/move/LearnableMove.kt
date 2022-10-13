package dev.hinaka.pokedex.domain.move

data class LearnableMove(
    val move: Move,
    val learnMethod: LearnMethod
)

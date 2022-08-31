package dev.hinaka.pokedex.domain

data class Pokemon(
    val id: Id,
    val name: String,
    val types: List<Type>,
    val imageUrl: String,
    val abilities: List<Ability>,
    val baseStats: Stats,
    val baseMoves: List<Move>,
)

package dev.hinaka.pokedex.data.network.model

data class NetworkPokemon(
    val id: Int,
    val name: String?,
    val typeIds: List<Int>?,
    val imageUrl: String?,
    val flavorText: String?,
    val height: Int?,
    val weight: Int?,
    val normalAbilityIds: List<Int>?,
    val hiddenAbilityId: Int?,
    val baseHp: Int?,
    val baseAttack: Int?,
    val baseDefense: Int?,
    val baseSpAttack: Int?,
    val baseSpDefense: Int?,
    val baseSpeed: Int?,
    val moveIds: List<Int>?,
)

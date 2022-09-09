package dev.hinaka.pokedex.navigation

import androidx.annotation.StringRes
import dev.hinaka.pokedex.R

enum class TopLevelDestination(
    val route: String,
    @StringRes val labelId: Int,
) {
    Pokemon("pokedex", R.string.destination_pokedex),
    Move("move", R.string.destination_move),
    Ability("ability", R.string.destination_ability),
    Item("item", R.string.destination_item),
    Location("location", R.string.destination_location),
    Type("type", R.string.destination_type),
    Nature("nature", R.string.destination_nature)
}

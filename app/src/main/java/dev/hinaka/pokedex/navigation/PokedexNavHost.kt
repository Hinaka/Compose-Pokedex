package dev.hinaka.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.hinaka.pokedex.feature.ability.navigation.abilityGraph
import dev.hinaka.pokedex.feature.item.navigation.itemGraph
import dev.hinaka.pokedex.feature.location.navigation.locationGraph
import dev.hinaka.pokedex.feature.move.navigation.moveGraph
import dev.hinaka.pokedex.feature.nature.navigation.natureGraph
import dev.hinaka.pokedex.feature.pokemon.navigation.pokemonGraph
import dev.hinaka.pokedex.feature.type.navigation.typeGraph
import dev.hinaka.pokedex.navigation.TopLevelDestination.Ability
import dev.hinaka.pokedex.navigation.TopLevelDestination.Item
import dev.hinaka.pokedex.navigation.TopLevelDestination.Location
import dev.hinaka.pokedex.navigation.TopLevelDestination.Move
import dev.hinaka.pokedex.navigation.TopLevelDestination.Nature
import dev.hinaka.pokedex.navigation.TopLevelDestination.Pokemon
import dev.hinaka.pokedex.navigation.TopLevelDestination.Type

@Composable
fun PokedexNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: TopLevelDestination = Pokemon,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        pokemonGraph(Pokemon.route)
        moveGraph(Move.route)
        abilityGraph(Ability.route)
        itemGraph(Item.route)
        locationGraph(Location.route)
        typeGraph(Type.route)
        natureGraph(Nature.route)
    }
}
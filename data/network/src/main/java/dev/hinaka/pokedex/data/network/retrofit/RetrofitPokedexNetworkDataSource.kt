/*
 * Copyright 2022 Hinaka (Trung Nguyễn Minh Trần)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.hinaka.pokedex.data.network.retrofit

import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.network.model.NetworkAbility
import dev.hinaka.pokedex.data.network.model.NetworkItem
import dev.hinaka.pokedex.data.network.model.NetworkLocation
import dev.hinaka.pokedex.data.network.model.NetworkMove
import dev.hinaka.pokedex.data.network.model.NetworkNature
import dev.hinaka.pokedex.data.network.model.NetworkPokemon
import dev.hinaka.pokedex.data.network.model.NetworkType
import dev.hinaka.pokedex.data.network.model.common.ids
import dev.hinaka.pokedex.data.network.response.common.id
import dev.hinaka.pokedex.data.network.response.common.isEn
import dev.hinaka.pokedex.data.network.retrofit.api.AbilityApi
import dev.hinaka.pokedex.data.network.retrofit.api.ItemApi
import dev.hinaka.pokedex.data.network.retrofit.api.LocationApi
import dev.hinaka.pokedex.data.network.retrofit.api.MoveApi
import dev.hinaka.pokedex.data.network.retrofit.api.NatureApi
import dev.hinaka.pokedex.data.network.retrofit.api.PokemonApi
import dev.hinaka.pokedex.data.network.retrofit.api.TypeApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class RetrofitPokedexNetworkDataSource @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val itemApi: ItemApi,
    private val moveApi: MoveApi,
    private val locationApi: LocationApi,
    private val abilityApi: AbilityApi,
    private val natureApi: NatureApi,
    private val typeApi: TypeApi
) : PokedexNetworkDataSource {

    override suspend fun getPokemons(offset: Int, limit: Int): List<NetworkPokemon> =
        coroutineScope {
            val ids = pokemonApi.getPokemons(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { getPokemon(it) }
            }.awaitAll()
        }

    private suspend fun getPokemon(id: Int): NetworkPokemon {
        val pokemon = pokemonApi.getPokemon(id)
        val species = pokemon.species?.id?.let {
            pokemonApi.getPokemonSpecies(it)
        }

        return NetworkPokemon(
            id = id,
            name = species?.names?.first { it.language.isEn }?.name,
            typeIds = pokemon.types?.sortedBy { it.slot }?.mapNotNull { it.type?.id },
            imageUrl = pokemon.sprites?.other?.officialArtwork?.front_default,
            flavorText = species?.flavor_text_entries?.first { it.language.isEn }?.flavor_text
                ?.replace("\n", " ")?.replace("\\f", ""),
            height = pokemon.height,
            weight = pokemon.weight,
            normalAbilityIds = pokemon.abilities?.filter { it.is_hidden == false }
                ?.mapNotNull { it.ability?.id },
            hiddenAbilityId = pokemon.abilities?.first { it.is_hidden == true }?.ability?.id,
            baseHp = pokemon.stats?.first { it.stat?.name == "hp" }?.base_stat,
            baseAttack = pokemon.stats?.first { it.stat?.name == "attack" }?.base_stat,
            baseDefense = pokemon.stats?.first { it.stat?.name == "defense" }?.base_stat,
            baseSpAttack = pokemon.stats?.first { it.stat?.name == "special-attack" }?.base_stat,
            baseSpDefense = pokemon.stats?.first { it.stat?.name == "special-defense" }?.base_stat,
            baseSpeed = pokemon.stats?.first { it.stat?.name == "speed" }?.base_stat,
            moveIds = pokemon.moves?.mapNotNull { it.move?.id }
        )
    }

    override suspend fun getItems(offset: Int, limit: Int): List<NetworkItem> =
        coroutineScope {
            val ids = itemApi.getItems(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { itemApi.getItem(it) }
            }.awaitAll()
        }

    override suspend fun getMoves(offset: Int, limit: Int): List<NetworkMove> =
        coroutineScope {
            val ids = moveApi.getMoves(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { moveApi.getMove(it) }
            }.awaitAll()
        }

    override suspend fun getLocations(offset: Int, limit: Int): List<NetworkLocation> =
        coroutineScope {
            val ids = locationApi.getLocations(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { locationApi.getLocation(it) }
            }.awaitAll()
        }

    override suspend fun getAbilities(offset: Int, limit: Int): List<NetworkAbility> =
        coroutineScope {
            val ids = abilityApi.getAbilities(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { abilityApi.getAbility(it) }
            }.awaitAll()
        }

    override suspend fun getAbilities(ids: List<Int>): List<NetworkAbility> =
        coroutineScope {
            ids.map {
                async { abilityApi.getAbility(it) }
            }.awaitAll()
        }

    override suspend fun getNatures(offset: Int, limit: Int): List<NetworkNature> =
        coroutineScope {
            val ids = natureApi.getNatures(
                offset = offset,
                limit = limit
            ).results.orEmpty().mapNotNull { it.id }

            ids.map {
                async { natureApi.getNature(it) }
            }.awaitAll()
        }

    override suspend fun getTypes(): List<NetworkType> =
        coroutineScope {
            val networkListResult = typeApi.getTypes()
            networkListResult.ids.orEmpty().map {
                async { typeApi.getType(it) }
            }.awaitAll()
        }
}

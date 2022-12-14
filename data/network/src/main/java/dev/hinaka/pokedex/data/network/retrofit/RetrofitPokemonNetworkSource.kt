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

import dev.hinaka.pokedex.data.network.api.PokemonApi
import dev.hinaka.pokedex.data.network.datasource.PokemonNetworkSource
import dev.hinaka.pokedex.data.network.model.NetworkPokemon
import dev.hinaka.pokedex.data.network.model.NetworkPokemon.LearnableMoves
import dev.hinaka.pokedex.data.network.response.common.id
import dev.hinaka.pokedex.data.network.response.common.isEn
import dev.hinaka.pokedex.data.network.response.pokemon.GetPokemonResponse
import dev.hinaka.pokedex.domain.move.LearnMethod.EGG
import dev.hinaka.pokedex.domain.move.LearnMethod.LEVEL
import dev.hinaka.pokedex.domain.move.LearnMethod.TM
import dev.hinaka.pokedex.domain.move.LearnMethod.TUTOR
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

internal class RetrofitPokemonNetworkSource @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonNetworkSource {

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
            name = species?.names?.firstOrNull { it.language.isEn }?.name,
            typeIds = pokemon.types?.sortedBy { it.slot }?.mapNotNull { it.type?.id },
            officialArtworkUrl = pokemon.sprites?.other?.officialArtwork?.front_default,
            frontDefaultUrl = pokemon.sprites?.front_default,
            frontShinyUrl = pokemon.sprites?.front_shiny,
            backDefaultUrl = pokemon.sprites?.back_default,
            backShinyUrl = pokemon.sprites?.back_shiny,
            flavorText = species?.flavor_text_entries?.firstOrNull { it.language.isEn }?.flavor_text
                ?.replace("\n", " ")?.replace("\\f", ""),
            height = pokemon.height,
            weight = pokemon.weight,
            normalAbilityIds = pokemon.abilities?.filter { it.is_hidden == false }
                ?.mapNotNull { it.ability?.id },
            hiddenAbilityId = pokemon.abilities?.firstOrNull { it.is_hidden == true }?.ability?.id,
            baseHp = pokemon.hp?.base_stat,
            baseAttack = pokemon.attack?.base_stat,
            baseDefense = pokemon.defense?.base_stat,
            baseSpAttack = pokemon.spAttack?.base_stat,
            baseSpDefense = pokemon.spDefense?.base_stat,
            baseSpeed = pokemon.speed?.base_stat,
            learnableMoves = pokemon.moves?.mapNotNull { response ->
                response.move?.id?.let {
                    val groupDetails = response.version_group_details?.first()
                    LearnableMoves(
                        moveId = it,
                        learnLevel = groupDetails?.level_learned_at,
                        learnMethod = when (groupDetails?.move_learn_method?.id) {
                            1 -> LEVEL
                            2 -> EGG
                            3 -> TUTOR
                            4 -> TM
                            else -> null
                        }
                    )
                }
            },
            genus = species?.genera?.firstOrNull { it.language.isEn }?.genus,
            genderRatio = species?.gender_rate,
            eggCycles = species?.hatch_counter?.inc(),
            eggGroupIds = species?.egg_groups?.mapNotNull { it.id },
            baseExp = pokemon.base_experience,
            baseHappiness = species?.base_happiness,
            catchRate = species?.capture_rate,
            effortHp = pokemon.hp?.effort,
            effortAttack = pokemon.attack?.effort,
            effortDefense = pokemon.defense?.effort,
            effortSpAttack = pokemon.spAttack?.effort,
            effortSpDefense = pokemon.spDefense?.effort,
            effortSpeed = pokemon.speed?.effort,
            growthRateId = species?.growth_rate?.id
        )
    }

    private val GetPokemonResponse.hp get() = stats?.firstOrNull { it.stat?.name == "hp" }
    private val GetPokemonResponse.attack
        get() = stats?.firstOrNull {
            it.stat?.name == "attack"
        }
    private val GetPokemonResponse.defense
        get() = stats?.firstOrNull {
            it.stat?.name == "defense"
        }
    private val GetPokemonResponse.spAttack
        get() = stats?.firstOrNull {
            it.stat?.name == "special-attack"
        }
    private val GetPokemonResponse.spDefense
        get() = stats?.firstOrNull {
            it.stat?.name == "special-defense"
        }
    private val GetPokemonResponse.speed get() = stats?.firstOrNull { it.stat?.name == "speed" }
}

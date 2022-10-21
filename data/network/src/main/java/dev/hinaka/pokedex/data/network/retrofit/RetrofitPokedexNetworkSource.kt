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

import dev.hinaka.pokedex.data.network.PokedexNetworkSource
import dev.hinaka.pokedex.data.network.source.AbilityNetworkSource
import dev.hinaka.pokedex.data.network.source.ItemNetworkSource
import dev.hinaka.pokedex.data.network.source.LocationNetworkSource
import dev.hinaka.pokedex.data.network.source.MoveNetworkSource
import dev.hinaka.pokedex.data.network.source.NatureNetworkSource
import dev.hinaka.pokedex.data.network.source.PokemonNetworkSource
import dev.hinaka.pokedex.data.network.source.TypeNetworkSource
import javax.inject.Inject

class RetrofitPokedexNetworkSource @Inject constructor(
    pokemonSource: PokemonNetworkSource,
    itemSource: ItemNetworkSource,
    moveSource: MoveNetworkSource,
    locationSource: LocationNetworkSource,
    abilitySource: AbilityNetworkSource,
    natureSource: NatureNetworkSource,
    typeSource: TypeNetworkSource,
) : PokedexNetworkSource,
    PokemonNetworkSource by pokemonSource,
    ItemNetworkSource by itemSource,
    MoveNetworkSource by moveSource,
    LocationNetworkSource by locationSource,
    AbilityNetworkSource by abilitySource,
    NatureNetworkSource by natureSource,
    TypeNetworkSource by typeSource

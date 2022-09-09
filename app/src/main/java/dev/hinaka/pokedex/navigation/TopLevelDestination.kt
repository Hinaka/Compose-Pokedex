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
package dev.hinaka.pokedex.navigation

import androidx.annotation.StringRes
import dev.hinaka.pokedex.R

enum class TopLevelDestination(
    val route: String,
    @StringRes val labelId: Int
) {
    Pokemon("pokedex", R.string.destination_pokedex),
    Move("move", R.string.destination_move),
    Ability("ability", R.string.destination_ability),
    Item("item", R.string.destination_item),
    Location("location", R.string.destination_location),
    Type("type", R.string.destination_type),
    Nature("nature", R.string.destination_nature)
}

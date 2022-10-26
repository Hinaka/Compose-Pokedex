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
package dev.hinaka.pokedex.data.database.model.pokemon

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "official_artwork") val officialArtwork: String?,
    @ColumnInfo(name = "front_default") val frontDefault: String?,
    @ColumnInfo(name = "front_shiny") val frontShiny: String?,
    @ColumnInfo(name = "back_default") val backDefault: String?,
    @ColumnInfo(name = "back_shiny") val backShiny: String?,
    @ColumnInfo(name = "type_1_id") val type1Id: Int?,
    @ColumnInfo(name = "type_2_id") val type2Id: Int?,
    @ColumnInfo(name = "ability_1_id") val ability1Id: Int?,
    @ColumnInfo(name = "ability_2_id") val ability2Id: Int?,
    @ColumnInfo(name = "hidden_ability_id") val hiddenAbilityId: Int?,
    @ColumnInfo(name = "flavor_text") val flavorText: String?,
    @ColumnInfo(name = "height") val height: Int?,
    @ColumnInfo(name = "weight") val weight: Int?,
    @ColumnInfo(name = "hp") val hp: Int?,
    @ColumnInfo(name = "attack") val attack: Int?,
    @ColumnInfo(name = "sp_attack") val spAttack: Int?,
    @ColumnInfo(name = "defense") val defense: Int?,
    @ColumnInfo(name = "sp_defense") val spDefense: Int?,
    @ColumnInfo(name = "speed") val speed: Int?,
    @ColumnInfo(name = "genus") val genus: String?,
    @Embedded val breeding: BreedingFields?
)

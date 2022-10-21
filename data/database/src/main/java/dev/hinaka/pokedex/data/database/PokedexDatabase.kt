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
package dev.hinaka.pokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.hinaka.pokedex.data.database.converter.PokemonTypesConverter
import dev.hinaka.pokedex.data.database.dao.AbilityDao
import dev.hinaka.pokedex.data.database.dao.ItemDao
import dev.hinaka.pokedex.data.database.dao.LocationDao
import dev.hinaka.pokedex.data.database.dao.MoveDao
import dev.hinaka.pokedex.data.database.dao.NatureDao
import dev.hinaka.pokedex.data.database.dao.PokemonDao
import dev.hinaka.pokedex.data.database.dao.TypeDao
import dev.hinaka.pokedex.data.database.model.AbilityEntity
import dev.hinaka.pokedex.data.database.model.ItemEntity
import dev.hinaka.pokedex.data.database.model.LocationEntity
import dev.hinaka.pokedex.data.database.model.move.MoveEntity
import dev.hinaka.pokedex.data.database.model.NatureEntity
import dev.hinaka.pokedex.data.database.model.pokemon.PokemonEntity
import dev.hinaka.pokedex.data.database.model.type.TypeDamageRelationEntity
import dev.hinaka.pokedex.data.database.model.type.TypeEntity
import dev.hinaka.pokedex.data.database.model.xref.PokemonAbilityXRef
import dev.hinaka.pokedex.data.database.model.xref.PokemonMoveXRef
import dev.hinaka.pokedex.data.database.model.xref.PokemonTypeXRef

@Database(
    version = 1,
    entities = [
        PokemonEntity::class,
        ItemEntity::class,
        MoveEntity::class,
        LocationEntity::class,
        AbilityEntity::class,
        NatureEntity::class,
        TypeEntity::class,
        TypeDamageRelationEntity::class,
        PokemonTypeXRef::class,
        PokemonMoveXRef::class,
        PokemonAbilityXRef::class
    ]
)
@TypeConverters(
    PokemonTypesConverter::class
)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun itemDao(): ItemDao
    abstract fun moveDao(): MoveDao
    abstract fun locationDao(): LocationDao
    abstract fun abilityDao(): AbilityDao
    abstract fun natureDao(): NatureDao
    abstract fun typeDao(): TypeDao
}

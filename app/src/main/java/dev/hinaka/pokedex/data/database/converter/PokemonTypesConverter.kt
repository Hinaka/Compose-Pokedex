package dev.hinaka.pokedex.data.database.converter

import androidx.room.TypeConverter
import dev.hinaka.pokedex.domain.Type
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PokemonTypesConverter {
    @TypeConverter
    fun fromString(value: String?): List<Type>? {
        return value?.let {
            Json.decodeFromString(it)
        }
    }

    @TypeConverter
    fun typesToString(types: List<Type>?): String? {
        return types?.let {
            Json.encodeToString(it)
        }
    }
}
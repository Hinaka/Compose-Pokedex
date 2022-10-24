package dev.hinaka.pokedex.data.repository

interface EggGroupRepository {
    suspend fun syncEggGroups()
}
package dev.hinaka.pokedex.data.repository

import dev.hinaka.pokedex.data.database.dao.PokemonDao
import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.repository.mapper.toDomain
import dev.hinaka.pokedex.data.repository.mapper.toEntity
import dev.hinaka.pokedex.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class OfflineFirstPokemonRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val networkDataSource: PokedexNetworkDataSource,
) : PokemonRepository {

    override fun getPokemonsStream(): Flow<List<Pokemon>> {
        return pokemonDao.getPokemonEntitiesStream()
            .map { it.toDomain() }
            .onEach {
                if (it.isEmpty()) {
                    fetchPokemonsFromNetwork()
                }
            }
    }

    private suspend fun fetchPokemonsFromNetwork() {
        val networkPokemons = networkDataSource.getPokemons()
        pokemonDao.insertOrReplacesPokemons(networkPokemons.toEntity())
    }
}
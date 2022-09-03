package dev.hinaka.pokedex.data.repository

import dev.hinaka.pokedex.data.network.PokedexNetworkDataSource
import dev.hinaka.pokedex.data.repository.mapper.toDomain
import dev.hinaka.pokedex.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonDataRepository @Inject constructor(
    private val networkDataSource: PokedexNetworkDataSource,
) : PokemonRepository {

    override fun getPokemonsStream(): Flow<List<Pokemon>> {
        return flow {
            emit(networkDataSource.getPokemons().map { it.toDomain() })
        }
    }
}
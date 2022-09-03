package dev.hinaka.pokedex.data.repository

import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.domain.Stats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonDataRepository @Inject constructor(): PokemonRepository {

    override fun getPokemonsStream(): Flow<List<Pokemon>> {
      return flow {
          emit(
              (1..20).map {
                  Pokemon(
                      id = Id(it),
                      name = "name $it",
                      types = emptyList(),
                      imageUrl = "Image $it",
                      abilities = emptyList(),
                      baseStats = Stats(0, 0, 0, 0, 0, 0),
                      baseMoves = emptyList(),
                  )
              }
          )
      }
    }
}
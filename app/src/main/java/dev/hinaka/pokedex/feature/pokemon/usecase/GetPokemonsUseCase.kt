package dev.hinaka.pokedex.feature.pokemon.usecase

import dev.hinaka.pokedex.data.repository.PokemonRepository
import dev.hinaka.pokedex.domain.Pokemon
import kotlinx.coroutines.flow.Flow

typealias GetPokemonsUseCase = @JvmSuppressWildcards suspend () -> Flow<List<Pokemon>>

fun getPokemons(
    repository: PokemonRepository,
) = repository.getPokemonsStream()

//class GetPokemonsUseCase @Inject constructor() {
//    operator fun invoke() = flow {
//        emit(
//            (1..20).map {
//                Pokemon(
//                    id = Id(it),
//                    name = "name $it",
//                    types = emptyList(),
//                    imageUrl = "Image $it",
//                    abilities = emptyList(),
//                    baseStats = Stats(0, 0, 0, 0, 0, 0),
//                    baseMoves = emptyList(),
//                )
//            }
//        )
//    }
//}
package dev.hinaka.pokedex.feature.pokemon.usecase

import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Pokemon
import dev.hinaka.pokedex.domain.Stats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias GetPokemonsUseCase = @JvmSuppressWildcards suspend () -> Flow<List<Pokemon>>

fun getPokemons() = flow {
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
package dev.hinaka.pokedex.feature.pokemon.usecase

import androidx.paging.PagingData
import dev.hinaka.pokedex.data.repository.PokemonRepository
import dev.hinaka.pokedex.domain.Pokemon
import kotlinx.coroutines.flow.Flow

typealias GetPokemonPagingUseCase = @JvmSuppressWildcards suspend () -> Flow<PagingData<Pokemon>>

fun getPokemonPaging(repository: PokemonRepository) = repository.getPokemonPagingStream()
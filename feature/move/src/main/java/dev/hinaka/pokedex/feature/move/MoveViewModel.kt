package dev.hinaka.pokedex.feature.move

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Move
import dev.hinaka.pokedex.feature.move.usecase.GetMovePagingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoveViewModel @Inject constructor(
    private val getMovePagingUseCase: GetMovePagingUseCase,
) : ViewModel() {

    private val movePagingFlow = flow {
        emit(getMovePagingUseCase(10).cachedIn(viewModelScope))
    }

    val uiState: StateFlow<MoveScreenUiState> = movePagingFlow.map {
        MoveScreenUiState(
            movePagingFlow = it
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MoveScreenUiState()
    )
}

data class MoveScreenUiState(
    val movePagingFlow: Flow<PagingData<Move>> = emptyFlow()
)
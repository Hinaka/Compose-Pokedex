package dev.hinaka.pokedex.feature.nature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Nature
import dev.hinaka.pokedex.feature.nature.usecase.GetNaturePagingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NatureViewModel @Inject constructor(
    private val getNaturePagingUseCase: GetNaturePagingUseCase,
) : ViewModel() {

    private val naturePagingFlow = flow {
        emit(getNaturePagingUseCase(10).cachedIn(viewModelScope))
    }

    val uiState: StateFlow<NatureScreenUiState> = naturePagingFlow.map {
        NatureScreenUiState(
            naturePagingFlow = it
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NatureScreenUiState()
    )
}

data class NatureScreenUiState(
    val naturePagingFlow: Flow<PagingData<Nature>> = emptyFlow()
)
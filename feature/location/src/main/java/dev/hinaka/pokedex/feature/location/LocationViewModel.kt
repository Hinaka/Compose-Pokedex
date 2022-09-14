package dev.hinaka.pokedex.feature.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Location
import dev.hinaka.pokedex.feature.location.usecase.GetLocationPagingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationPagingUseCase: GetLocationPagingUseCase,
) : ViewModel() {

    private val locationPagingFlow = flow {
        emit(getLocationPagingUseCase(10).cachedIn(viewModelScope))
    }

    val uiState: StateFlow<LocationViewUiState> = locationPagingFlow.map {
        LocationViewUiState(
            locationPagingFlow = it
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = LocationViewUiState()
    )
}

data class LocationViewUiState(
    val locationPagingFlow: Flow<PagingData<Location>> = emptyFlow(),
)
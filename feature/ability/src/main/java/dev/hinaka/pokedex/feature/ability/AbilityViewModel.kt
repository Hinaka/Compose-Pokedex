package dev.hinaka.pokedex.feature.ability

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Ability
import dev.hinaka.pokedex.feature.ability.usecase.GetAbilityPagingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AbilityViewModel @Inject constructor(
    private val getAbilityPagingUseCase: GetAbilityPagingUseCase,
) : ViewModel() {

    private val abilityPagingFlow = flow {
        emit(getAbilityPagingUseCase(10).cachedIn(viewModelScope))
    }

    val uiState: StateFlow<AbilityScreenUiState> = abilityPagingFlow.map {
        AbilityScreenUiState(
            abilityPagingFlow = it
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AbilityScreenUiState()
    )
}

data class AbilityScreenUiState(
    val abilityPagingFlow: Flow<PagingData<Ability>> = emptyFlow()
)
package dev.hinaka.pokedex.feature.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Item
import dev.hinaka.pokedex.feature.item.usecase.GetItemPagingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    getItemPagingUseCase: GetItemPagingUseCase
) : ViewModel() {

    private val itemPagingFlow = flow {
        emit(getItemPagingUseCase(10).cachedIn(viewModelScope))
    }

    val uiState: StateFlow<ItemScreenUiState> = itemPagingFlow.map {
        ItemScreenUiState(
            itemPagingFlow = it
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ItemScreenUiState()
    )
}

data class ItemScreenUiState(
    val itemPagingFlow: Flow<PagingData<Item>> = emptyFlow()
)
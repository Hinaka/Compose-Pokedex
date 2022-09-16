package dev.hinaka.pokedex.feature.initialload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.feature.initialload.usecase.SyncTypesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitialLoadViewModel @Inject constructor(
    private val syncTypesUseCase: SyncTypesUseCase,
) : ViewModel() {
    private var _isReady = false
    val isReady get() = _isReady

    init {
        viewModelScope.launch {
            syncTypesUseCase()
            _isReady = true
        }
    }
}
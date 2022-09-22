package dev.hinaka.pokedex.feature.type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.domain.type.Type.Identifier.NORMAL
import dev.hinaka.pokedex.feature.type.usecase.GetTypeDamageTakenRelationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeViewModel @Inject constructor(
    private val getTypeDamageTakenRelationsUseCase: GetTypeDamageTakenRelationsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TypeScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val damageRelations = getTypeDamageTakenRelationsUseCase(
                Type(
                    id = Id(7),
                    identifier = NORMAL,
                    name = "normal"
                )
            )
            _uiState.update {
                it.copy(
                    damageRelationMap = damageRelations
                )
            }
        }
    }
}

data class TypeScreenUiState(
    val damageRelationMap: Map<Type, DamageFactor> = emptyMap(),
)
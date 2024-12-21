package com.sd.bforbanktest.feature.pokemondetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sd.bforbank.core.common.LogService
import com.sd.bforbank.core.ui.MviViewModel
import com.sd.bforbanktest.feature.pokemondetail.domain.PokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    logService: LogService,
    private val useCase: PokemonDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : MviViewModel<PokemonDetailEvent, PokemonDetailState>(
    PokemonDetailState(checkNotNull(savedStateHandle[POKEMON_NAME])),
    logService,
) {

    private val pokemonName: String = checkNotNull(savedStateHandle[POKEMON_NAME])

    init {
        loadDetail()

        on<PokemonDetailEvent.ErrorRetryButtonClicked> { loadDetail() }
    }

    private fun loadDetail() {
        viewModelScope.launch {
            useCase.getDetail(pokemonName).onSuccess { data ->
                    internalState.update { state ->
                        state.copy(status = PokemonDetailState.Status.Success(data))
                    }
                }.onFailure {
                    internalState.update { state ->
                        state.copy(status = PokemonDetailState.Status.Error)
                    }
                }
        }
    }
}

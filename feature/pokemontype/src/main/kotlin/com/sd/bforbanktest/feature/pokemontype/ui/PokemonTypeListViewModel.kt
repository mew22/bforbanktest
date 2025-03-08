package com.sd.bforbanktest.feature.pokemontype.ui

import androidx.lifecycle.viewModelScope
import com.sd.bforbank.core.common.LogService
import com.sd.bforbank.core.ui.MviViewModel
import com.sd.bforbanktest.feature.pokemontype.domain.PokemonTypeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonTypeListViewModel @Inject constructor(
    logService: LogService,
    private val useCase: PokemonTypeListUseCase,
) : MviViewModel<PokemonTypeListEvent, PokemonTypeListState>(PokemonTypeListState.Idle, logService) {

    init {
        loadTypes()
    }

    private fun loadTypes() {
        viewModelScope.launch {
            useCase.loadTypes().fold(
                onSuccess = { data ->
                    internalState.update {
                        PokemonTypeListState.Success(data.toImmutableList())
                    }
                },
                onFailure = {
                    internalState.update { PokemonTypeListState.Error }
                }
            )
        }
    }
}

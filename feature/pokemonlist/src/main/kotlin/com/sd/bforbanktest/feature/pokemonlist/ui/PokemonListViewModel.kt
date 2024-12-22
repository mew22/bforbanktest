package com.sd.bforbanktest.feature.pokemonlist.ui

import androidx.lifecycle.viewModelScope
import com.sd.bforbank.core.common.LogService
import com.sd.bforbank.core.ui.MviViewModel
import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    logService: LogService,
    private val useCase: PokemonListItemUseCase,
) : MviViewModel<PokemonListEvent, PokemonListState>(PokemonListState(), logService) {

    init {
        observeData()
        loadMore()

        on<PokemonListEvent.ListReachBottom> {
            loadMore()
        }
    }

    private fun loadMore() {
        internalState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            useCase.loadMore(limit = ITEM_PER_PAGE).onSuccess {
                internalState.update { state ->
                    state.copy(isLoading = false)
                }
            }.onFailure {
                internalState.update { state ->
                    state.copy(isLoading = false, status = PokemonListState.Status.Error)
                }
            }
        }
    }

    private fun observeData() {
        useCase.data.onEach { data ->
            internalState.update { state ->
                state.copy(
                    status = PokemonListState.Status.Success(
                        list = state.asSuccessOrNull()
                        ?.run {
                            list.apply {
                                clear()
                                addAll(data)
                            }
                        }
                        ?: data.toMutableList()
                    )
                )
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        private const val ITEM_PER_PAGE = 20
    }
}

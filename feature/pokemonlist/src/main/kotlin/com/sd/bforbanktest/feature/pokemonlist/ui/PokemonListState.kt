package com.sd.bforbanktest.feature.pokemonlist.ui

import androidx.compose.runtime.Immutable
import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class PokemonListState(
    val isLoading: Boolean = false,
    val status: Status = Status.Idle,
) {
    sealed class Status {
        data object Idle : Status()
        data class Success(
            val list: ImmutableList<PokemonListItem> = persistentListOf(),
        ) : Status()
        data object Error : Status()
    }
}

package com.sd.bforbanktest.feature.pokemonlist.ui

import androidx.compose.runtime.Immutable
import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItem

@Immutable
data class PokemonListState(
    val isLoading: Boolean = false,
    val status: Status = Status.Idle,
) {
    sealed class Status {
        data object Idle : Status()
        data class Success(
            // We need to keep the same list instance to preserve scrolling state after change, and use MutableList instead of List or PersitentList
            val list: MutableList<PokemonListItem> = mutableListOf(),
        ) : Status()

        data object Error : Status()
    }
}

fun PokemonListState.asSuccessOrNull(): PokemonListState.Status.Success? =
    this.status as? PokemonListState.Status.Success

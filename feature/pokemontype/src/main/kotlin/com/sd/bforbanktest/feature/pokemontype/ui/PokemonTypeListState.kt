package com.sd.bforbanktest.feature.pokemontype.ui

import androidx.compose.runtime.Immutable
import com.sd.bforbanktest.feature.pokemontype.domain.PokemonTypeListItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
sealed class PokemonTypeListState {
    data object Idle : PokemonTypeListState()
    data class Success(
        val list: ImmutableList<PokemonTypeListItem> = persistentListOf(),
    ) : PokemonTypeListState()

    data object Error : PokemonTypeListState()
}

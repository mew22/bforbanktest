package com.sd.bforbanktest.feature.pokemondetail.ui

import androidx.compose.runtime.Immutable
import com.sd.bforbanktest.feature.pokemondetail.domain.PokemonDetail

@Immutable
data class PokemonDetailState(
    val name: String = "",
    val status: Status = Status.Loading,
) {
    sealed class Status {
        data object Loading : Status()
        data class Success(
            val pokemon: PokemonDetail,
        ) : Status()
        data object Error : Status()
    }
}

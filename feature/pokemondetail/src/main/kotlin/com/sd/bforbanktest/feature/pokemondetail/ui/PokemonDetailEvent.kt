package com.sd.bforbanktest.feature.pokemondetail.ui

sealed class PokemonDetailEvent {
    data object ErrorRetryButtonClicked : PokemonDetailEvent()
}

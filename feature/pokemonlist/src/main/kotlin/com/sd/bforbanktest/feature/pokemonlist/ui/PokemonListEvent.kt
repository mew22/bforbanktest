package com.sd.bforbanktest.feature.pokemonlist.ui

sealed class PokemonListEvent {
    data object ListReachBottom : PokemonListEvent()
    data object BackButtonClicked : PokemonListEvent()
}

package com.sd.bforbanktest.feature.pokemontype.ui

sealed class PokemonTypeListEvent {
    data object BackButtonClicked : PokemonTypeListEvent()
}

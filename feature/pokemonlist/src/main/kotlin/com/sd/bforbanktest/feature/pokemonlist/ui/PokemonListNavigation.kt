package com.sd.bforbanktest.feature.pokemonlist.ui

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object PokemonListNavigation

fun NavController.navigateToPokemonList(navOptions: NavOptions? = null) {
    navigate(PokemonListNavigation, navOptions)
}

fun NavGraphBuilder.pokemonListScreen() {
    composable<PokemonListNavigation> {
        val viewModel = hiltViewModel<PokemonListViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        PokemonListRoute(
            state = state,
            dispatch = viewModel::dispatch,
        )
    }
}

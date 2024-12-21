package com.sd.bforbanktest.feature.pokemondetail.ui

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

const val POKEMON_NAME = "pokemonName"

@Serializable
data class PokemonDetailNavigation(val pokemonName: String)

fun NavController.navigateToPokemonDetail(pokemonName: String, navOptions: NavOptions? = null) {
    navigate(PokemonDetailNavigation(pokemonName), navOptions)
}

fun NavGraphBuilder.pokemonDetailScreen(popUp: () -> Unit) {
    composable<PokemonDetailNavigation> {
        val viewModel = hiltViewModel<PokemonDetailViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        PokemonDetailRoute(
            state = state,
            dispatch = viewModel::dispatch,
            popUp = popUp,
        )
    }
}

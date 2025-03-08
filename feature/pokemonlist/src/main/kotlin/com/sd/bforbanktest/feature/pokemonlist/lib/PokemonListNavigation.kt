package com.sd.bforbanktest.feature.pokemonlist.lib

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sd.bforbanktest.feature.pokemonlist.ui.PokemonListRoute
import com.sd.bforbanktest.feature.pokemonlist.ui.PokemonListViewModel
import kotlinx.serialization.Serializable

const val TYPE_NAME = "typeName"

@Serializable
data class PokemonListNavigation(val typeName: String)

fun NavController.navigateToPokemonList(typeName: String, navOptions: NavOptions? = null) {
    navigate(PokemonListNavigation(typeName), navOptions)
}

fun NavGraphBuilder.pokemonListScreen(
    navigateToDetail: (String) -> Unit,
    popUp: () -> Unit,
) {
    composable<PokemonListNavigation> {
        val viewModel = hiltViewModel<PokemonListViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        PokemonListRoute(
            state = state,
            dispatch = viewModel::dispatch,
            navigateToDetail = navigateToDetail,
            popUp = popUp,
        )
    }
}

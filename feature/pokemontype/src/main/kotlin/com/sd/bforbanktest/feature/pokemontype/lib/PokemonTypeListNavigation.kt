package com.sd.bforbanktest.feature.pokemontype.lib

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sd.bforbanktest.feature.pokemontype.ui.PokemonTypeListRoute
import com.sd.bforbanktest.feature.pokemontype.ui.PokemonTypeListViewModel
import kotlinx.serialization.Serializable

@Serializable
object PokemonTypeListNavigation

fun NavController.navigateToPokemonTypeList(navOptions: NavOptions? = null) {
    navigate(PokemonTypeListNavigation, navOptions)
}

fun NavGraphBuilder.pokemonTypeListScreen(navigateToList: (String) -> Unit) {
    composable<PokemonTypeListNavigation> {
        val viewModel = hiltViewModel<PokemonTypeListViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        PokemonTypeListRoute(
            state = state,
            navigateToPokemonList = navigateToList,
        )
    }
}

package com.sd.bforbanktest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sd.bforbanktest.feature.pokemondetail.lib.navigateToPokemonDetail
import com.sd.bforbanktest.feature.pokemondetail.lib.pokemonDetailScreen
import com.sd.bforbanktest.feature.pokemonlist.lib.navigateToPokemonList
import com.sd.bforbanktest.feature.pokemonlist.lib.pokemonListScreen
import com.sd.bforbanktest.feature.pokemontype.lib.PokemonTypeListNavigation
import com.sd.bforbanktest.feature.pokemontype.lib.pokemonTypeListScreen
import kotlin.reflect.KClass

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: KClass<*> = PokemonTypeListNavigation::class,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        pokemonTypeListScreen(navigateToList = navController::navigateToPokemonList)
        pokemonListScreen(
            navigateToDetail = navController::navigateToPokemonDetail,
            popUp = navController::safePopBackStack
        )
        pokemonDetailScreen(popUp = navController::safePopBackStack)
    }
}

@Suppress("ReplaceSafeCallChainWithRun")
fun NavHostController.safePopBackStack() {
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        popBackStack()
    }
}

package com.sd.bforbanktest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sd.bforbanktest.feature.pokemonlist.ui.PokemonListNavigation
import com.sd.bforbanktest.feature.pokemonlist.ui.pokemonListScreen
import kotlin.reflect.KClass

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: KClass<*> = PokemonListNavigation::class,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        pokemonListScreen()
    }
}

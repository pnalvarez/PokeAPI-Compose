package com.example.pokeapi.common

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokeapi.details.PokemonDetailsScreen
import com.example.pokeapi.list.PokemonListScreen

@Composable
fun PokeAPIApp(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") { PokemonListScreen(navController) }
        composable(
            "details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }), enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            }
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("id")
                ?.let { PokemonDetailsScreen(navController = navController, name = it) }
        }
    }
}
package ru.kpfu.itis.pokemonapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.kpfu.itis.pokemonapp.presentation.model.Pokemon
import ru.kpfu.itis.pokemonapp.presentation.ui.screen.info.PokemonInfoScreen
import ru.kpfu.itis.pokemonapp.presentation.ui.screen.list.PokemonListScreen

sealed class Screen(
    val route: String,
) {
    object PokemonList : Screen("pokemonList")

    object PokemonInfo : Screen("pokemonInfo")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.PokemonList.route
) {
    Scaffold { padding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            Modifier.padding(padding)
        ) {
            composable(Screen.PokemonList.route) { PokemonListScreen(navController) }
            composable(Screen.PokemonInfo.route) {
                val result = navController.previousBackStackEntry?.savedStateHandle?.get<Pokemon>("pokemon")
                result?.let {
                    PokemonInfoScreen(pokemon = it)
                }
            }
        }
    }

}
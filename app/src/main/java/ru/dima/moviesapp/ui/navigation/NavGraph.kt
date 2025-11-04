package ru.dima.moviesapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import ru.dima.moviesapp.ui.screens.movies.MoviesScreen
import ru.dima.moviesapp.ui.screens.details.MovieDetailsScreen
import ru.dima.moviesapp.ui.screens.favorites.FavoritesScreen
import ru.dima.moviesapp.ui.screens.movies.MoviesViewModel
import ru.dima.moviesapp.data.api.RetrofitClient
import ru.dima.moviesapp.data.repository.MoviesRepository
import androidx.compose.ui.platform.LocalContext

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val repository = remember { MoviesRepository(RetrofitClient.getApi(context)) }
    val moviesViewModel = remember { MoviesViewModel(repository) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("movies") },
                    icon = { Icon(Icons.Default.Check, contentDescription = null) },
                    label = { Text("Фильмы") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("favorites") },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    label = { Text("Избранное") }
                )
            }
        }
    ) { padding ->
        NavHost(navController, startDestination = "movies", Modifier.padding(padding)) {
            composable("movies") {
                MoviesScreen(viewModel = moviesViewModel) { movieId ->
                    navController.navigate("details/$movieId")
                }
            }
            composable(
                "details/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("movieId") ?: 0
                MovieDetailsScreen(repository = repository, movieId = id)
            }
            composable("favorites") {
                FavoritesScreen()
            }
        }
    }
}


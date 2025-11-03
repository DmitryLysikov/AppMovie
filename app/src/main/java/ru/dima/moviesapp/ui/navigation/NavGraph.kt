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
import ru.dima.moviesapp.data.api.KinopoiskApi
import ru.dima.moviesapp.data.repository.MoviesRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.kinopoisk.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(KinopoiskApi::class.java)
    val repository = MoviesRepository(api)
    val moviesViewModel = MoviesViewModel(repository)

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

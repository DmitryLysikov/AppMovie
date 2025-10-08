package ru.dima.moviesapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import ru.dima.moviesapp.ui.screens.details.MovieDetailsScreen
import ru.dima.moviesapp.ui.screens.details.MovieDetailsScreen
import ru.dima.moviesapp.ui.screens.favorites.FavoritesScreen
import ru.dima.moviesapp.ui.screens.movies.MoviesScreen
import ru.dima.moviesapp.ui.screens.movies.MoviesViewModel

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Movies : Screen("movies", "Фильмы", Icons.Default.Check)
    object Favorites : Screen("favorites", "Избранное", Icons.Default.Favorite)
    object Details : Screen("details/{movieId}", "Детали", Icons.Default.Info)
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: MoviesViewModel = viewModel()

    val items = listOf(Screen.Movies, Screen.Favorites)
    var showBottomBar by remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = screen.label) },
                            label = { Text(screen.label) },
                            selected = currentDestination?.route == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Movies.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Movies.route) {
                showBottomBar = true
                MoviesScreen(viewModel) { movieId ->
                    showBottomBar = false
                    navController.navigate("details/$movieId")
                }
            }
            composable(Screen.Favorites.route) {
                showBottomBar = true
                FavoritesScreen()
            }
            composable(
                "details/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                showBottomBar = false
                val id = backStackEntry.arguments?.getInt("movieId")
                val movie = viewModel.getMovieById(id ?: -1)
                if (movie != null) MovieDetailsScreen(movie)
            }
        }
    }
}

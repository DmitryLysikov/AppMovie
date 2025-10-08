package ru.dima.moviesapp.ui.screens.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.dima.moviesapp.data.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(viewModel: MoviesViewModel, onMovieClick: (Int) -> Unit) {
    val movies by viewModel.movies.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Фильмы") }) }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(movies) { movie ->
                MovieItem(movie = movie, onClick = { onMovieClick(movie.id) })
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(movie.title, style = MaterialTheme.typography.titleLarge)
                Text("${movie.year} • ${movie.genre}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

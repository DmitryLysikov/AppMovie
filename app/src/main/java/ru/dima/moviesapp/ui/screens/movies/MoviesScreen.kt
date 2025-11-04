package ru.dima.moviesapp.ui.screens.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.dima.moviesapp.data.model.Movie
import ru.dima.moviesapp.R

@Composable
fun MoviesScreen(viewModel: MoviesViewModel, onMovieClick: (Int) -> Unit) {
    val state by viewModel.moviesState.collectAsState()

    when (state) {
        is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        is UiState.Error -> Text("Ошибка: ${(state as UiState.Error).message}")
        is UiState.Success -> {
            val movies = (state as UiState.Success<List<Movie>>).data
            LazyColumn {
                items(movies) { movie ->
                    MovieItem(movie, onClick = { onMovieClick(movie.id) })
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    val title = movie.name ?: movie.alternativeName ?: movie.enName ?: "Без названия"
    val year = movie.year?.toString() ?: "—"
    val imageUrl = movie.poster?.previewUrl?.replace("http://", "https://")
        ?: movie.poster?.url?.replace("http://", "https://")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                placeholder = null,
                error = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleLarge)
                Text(year, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

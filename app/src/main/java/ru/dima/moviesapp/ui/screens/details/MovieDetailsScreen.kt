package ru.dima.moviesapp.ui.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.dima.moviesapp.data.model.Actor
import ru.dima.moviesapp.data.repository.MoviesRepository

@Composable
fun MovieDetailsScreen(repository: MoviesRepository, movieId: Int) {
    var movie by remember { mutableStateOf<ru.dima.moviesapp.data.model.MovieDetails?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(movieId) {
        movie = repository.getMovieDetails(movieId)
        isLoading = false
    }

    if (isLoading) {
        CircularProgressIndicator(Modifier.padding(16.dp))
    } else {
        movie?.let { m ->
            Column(Modifier.padding(16.dp)) {
                AsyncImage(
                    model = m.poster?.url,
                    contentDescription = m.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Text(m.name ?: "Без названия", style = MaterialTheme.typography.headlineMedium)
                Text(m.description ?: "", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(16.dp))
                Text("Актёры:", style = MaterialTheme.typography.titleMedium)
                LazyRow {
                    items(m.persons ?: emptyList()) { actor ->
                        ActorCard(actor)
                    }
                }
            }
        }
    }
}

@Composable
fun ActorCard(actor: Actor) {
    Column(Modifier.padding(8.dp)) {
        AsyncImage(
            model = actor.photo,
            contentDescription = actor.name,
            modifier = Modifier.size(100.dp)
        )
        Text(actor.name ?: "Неизвестно", style = MaterialTheme.typography.bodySmall)
    }
}

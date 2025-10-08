package ru.dima.moviesapp.ui.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import ru.dima.moviesapp.data.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(movie: Movie) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(movie.title) }) }
    ) { innerPadding ->
        ConstraintLayout(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val (poster, title, genre, desc) = createRefs()

            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .constrainAs(poster) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(poster.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                }
            )

            Text(
                text = movie.genre,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.constrainAs(genre) {
                    top.linkTo(title.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                }
            )

            Text(
                text = movie.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.constrainAs(desc) {
                    top.linkTo(genre.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}

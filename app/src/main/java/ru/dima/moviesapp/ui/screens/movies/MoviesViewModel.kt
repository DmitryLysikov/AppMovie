package ru.dima.moviesapp.ui.screens.movies

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.dima.moviesapp.data.model.Movie

class MoviesViewModel : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    init {
        _movies.value = listOf(
            Movie(
                id = 1,
                title = "Министерство неджентльменских дел",
                year = "2024",
                genre = "Боевик, драма, военный",
                posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10671298/72d3ef84-c785-46aa-8a8d-16e0f0847c56/600x900",
                description = "Отряду авантюристов поручают невыполнимую миссию..."
            ),
            Movie(
                id = 2,
                title = "Inception",
                year = "2010",
                genre = "Фантастика, триллер",
                posterUrl = "https://picsum.photos/300/450",
                description = "Фильм о проникновении в сны и изменении сознания."
            )
        )
    }

    fun getMovieById(id: Int): Movie? = _movies.value.find { it.id == id }
}

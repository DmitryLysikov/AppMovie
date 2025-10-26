package ru.dima.moviesapp.ui.screens.movies

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.dima.moviesapp.data.model.*

class MoviesViewModel : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _navigateToDetails = MutableStateFlow<Int?>(null)
    val navigateToDetails: StateFlow<Int?> = _navigateToDetails.asStateFlow()

    init {
        _movies.value = listOf(
            Movie(
                id = 1,
                title = "Министерство неджентльменских дел",
                year = "2024",
                genre = "Боевик, драма, военный",
                posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10671298/72d3ef84-c785-46aa-8a8d-16e0f0847c56/600x900",
                description = "Отряду авантюристов поручают невыполнимую миссию...",
                actors = listOf(
                    Actor("Генри Кавилл", "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/31b83320-2a3d-4b6e-b71e-17f02a92b219/300x"),
                    Actor("Эйса Гонсалес", "https://avatars.mds.yandex.net/get-kinopoisk-image/1946459/6d37331e-1b18-4b1b-9c64-b95df1e6a6d9/300x"),
                    Actor("Алан Ричсон", "https://avatars.mds.yandex.net/get-kinopoisk-image/4303601/1b1a0782-fbe9-49f3-8dc7-f3acb8ec97e3/300x")
                )
            ),
            Movie(
                id = 2,
                title = "Inception",
                year = "2010",
                genre = "Фантастика, триллер",
                posterUrl = "https://m.media-amazon.com/images/I/71JxqD2bZCL._AC_SY679_.jpg",
                description = "Фильм о проникновении в сны и изменении сознания.",
                actors = listOf(
                    Actor("Леонардо Ди Каприо", "https://avatars.mds.yandex.net/get-kinopoisk-image/1599028/3b4a997b-3563-4e9d-9a3a-3a8cfb1ad631/300x"),
                    Actor("Эллен Пейдж", "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/4a01125b-05cd-48f5-b4ef-04b92b56b2d3/300x"),
                    Actor("Джозеф Гордон-Левитт", "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/34e63d9c-cdfb-4967-a8c2-c5e95ce2c98a/300x")
                )
            )
        )
    }

    fun getMovieById(id: Int): Movie? = _movies.value.find { it.id == id }

    fun onMovieClicked(movieId: Int) {
        _navigateToDetails.value = movieId
    }

    fun onNavigated() {
        _navigateToDetails.value = null
    }
}

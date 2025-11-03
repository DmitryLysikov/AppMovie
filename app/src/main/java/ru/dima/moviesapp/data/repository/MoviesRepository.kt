package ru.dima.moviesapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dima.moviesapp.data.api.KinopoiskApi
import ru.dima.moviesapp.data.model.Movie
import ru.dima.moviesapp.data.model.MovieDetails

class MoviesRepository(private val api: KinopoiskApi) {

    suspend fun getMovies(): List<Movie> = withContext(Dispatchers.IO) {
        api.getMovies().docs
    }

    suspend fun getMovieDetails(id: Int): MovieDetails = withContext(Dispatchers.IO) {
        api.getMovieDetails(id)
    }
}

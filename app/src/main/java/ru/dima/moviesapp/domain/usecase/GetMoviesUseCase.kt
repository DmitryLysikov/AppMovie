package ru.dima.moviesapp.domain.usecase

import ru.dima.moviesapp.data.repository.MoviesRepository

class GetMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke() = repository.getMovies()
}

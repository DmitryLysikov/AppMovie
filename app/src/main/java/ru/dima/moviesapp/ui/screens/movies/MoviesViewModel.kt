package ru.dima.moviesapp.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.dima.moviesapp.data.repository.MoviesRepository
import ru.dima.moviesapp.data.model.Movie

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _moviesState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val moviesState: StateFlow<UiState<List<Movie>>> = _moviesState

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            try {
                _moviesState.value = UiState.Loading
                val movies = repository.getMovies()
                _moviesState.value = UiState.Success(movies)
            } catch (e: Exception) {
                _moviesState.value = UiState.Error("Ошибка загрузки: ${e.localizedMessage}")
            }
        }
    }
}

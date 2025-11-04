package ru.dima.moviesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    val id: Int,
    val name: String?,
    val description: String?,
    val year: Int?,
    val poster: Poster?,
    val persons: List<Actor>?
)

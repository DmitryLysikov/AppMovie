package ru.dima.moviesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val name: String? = null,
    val alternativeName: String? = null,
    val enName: String? = null,
    val year: Int? = null,
    val poster: Poster? = null
)

@Serializable
data class Poster(
    val url: String? = null,
    val previewUrl: String? = null
)


@Serializable
data class MovieResponse(val docs: List<Movie>)

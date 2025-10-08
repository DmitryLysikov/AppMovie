package ru.dima.moviesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val year: String,
    val genre: String,
    val posterUrl: String,
    val description: String
)

package ru.dima.moviesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Actor(
    val id: Int,
    val name: String?,
    val photo: String?
)

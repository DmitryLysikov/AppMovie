package ru.dima.moviesapp.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import ru.dima.moviesapp.BuildConfig
import ru.dima.moviesapp.data.model.MovieResponse
import ru.dima.moviesapp.data.model.MovieDetails

interface KinopoiskApi {

    @GET("v1.3/movie")
    suspend fun getMovies(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): MovieResponse

    @GET("v1.3/movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): MovieDetails
}

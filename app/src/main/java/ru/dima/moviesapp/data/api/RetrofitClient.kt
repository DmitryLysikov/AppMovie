package ru.dima.moviesapp.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dima.moviesapp.BuildConfig

object RetrofitClient {

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-API-KEY", BuildConfig.KINOPOISK_API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    val api: KinopoiskApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KinopoiskApi::class.java)
    }
}

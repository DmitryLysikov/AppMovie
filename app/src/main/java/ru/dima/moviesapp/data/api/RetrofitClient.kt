package ru.dima.moviesapp.data.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dima.moviesapp.BuildConfig

object RetrofitClient {

    private var apiInstance: KinopoiskApi? = null

    fun getApi(context: Context): KinopoiskApi {
        if (apiInstance != null) return apiInstance!!

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-API-KEY", BuildConfig.KINOPOISK_API_KEY)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(ChuckerInterceptor(context))
            .build()

        apiInstance = Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KinopoiskApi::class.java)

        return apiInstance!!
    }
}

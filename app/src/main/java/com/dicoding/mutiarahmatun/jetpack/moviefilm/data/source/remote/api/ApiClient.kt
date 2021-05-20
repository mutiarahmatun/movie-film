package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.api

import com.dicoding.mutiarahmatun.jetpack.moviefilm.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val httpClient = OkHttpClient()


    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder().apply {
            client(httpClient)
            baseUrl(BuildConfig.BASE_URL_TMDB)
            addConverterFactory(GsonConverterFactory.create())
        }
    }


    val instance: ApiService by lazy {
        retrofit
            .build()
            .create(ApiService::class.java)
    }
}
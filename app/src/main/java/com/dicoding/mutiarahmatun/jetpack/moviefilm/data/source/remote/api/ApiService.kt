package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.api

import com.dicoding.mutiarahmatun.jetpack.moviefilm.BuildConfig
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.response.ListResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.response.MovieResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<ListResponse<MovieResponse>>

    @GET("tv/on_the_air")
    fun getTvShowOnTheAir(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<ListResponse<TvShowResponse>>

}
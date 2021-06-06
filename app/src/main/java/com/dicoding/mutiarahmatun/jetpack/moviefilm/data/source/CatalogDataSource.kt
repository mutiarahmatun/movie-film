package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source

import androidx.lifecycle.LiveData

interface CatalogDataSource {

    fun getNowPlayingMovies(): LiveData<List<FilmEntity>>

    fun getMovieDetail(movieId: Int): LiveData<FilmEntity>

    fun getTvShowOnTheAir(): LiveData<List<FilmEntity>>

    fun getTvShowDetail(tvShowId: Int): LiveData<FilmEntity>
}
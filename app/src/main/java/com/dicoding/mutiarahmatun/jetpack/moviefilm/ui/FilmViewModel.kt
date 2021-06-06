package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.CatalogRepository

class FilmViewModel (private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getListNowPlayingMovies(): LiveData<List<FilmEntity>> = catalogRepository.getNowPlayingMovies()

    fun getListOnTheAirTvShows(): LiveData<List<FilmEntity>> = catalogRepository.getTvShowOnTheAir()

}
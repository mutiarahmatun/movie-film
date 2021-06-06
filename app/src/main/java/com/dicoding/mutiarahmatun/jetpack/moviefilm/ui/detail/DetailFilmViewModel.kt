package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.CatalogRepository

class DetailFilmViewModel (private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<FilmEntity> =
        catalogRepository.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<FilmEntity> =
            catalogRepository.getTvShowDetail(tvShowId)

}
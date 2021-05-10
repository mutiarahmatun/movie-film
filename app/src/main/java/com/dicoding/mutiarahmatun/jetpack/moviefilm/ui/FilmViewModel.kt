package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui

import androidx.lifecycle.ViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.FilmEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.DataDummy

class FilmViewModel : ViewModel() {

    fun getListTvShow() : List<FilmEntity> = DataDummy.generateDummyTvShows()

    fun getListMovie() : List<FilmEntity> = DataDummy.generateDummyMovies()
}
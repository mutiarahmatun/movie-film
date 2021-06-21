package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.MovieFilmRepository
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.valueobject.ResourceData
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val catalogRepository: MovieFilmRepository)
    : ViewModel() {

    fun getListNowPlayingMovies(): LiveData<ResourceData<PagedList<MovieEntity>>> = catalogRepository.getNowPlayingMovies()

    fun getListOnTheAirTvShows(): LiveData<ResourceData<PagedList<TvShowEntity>>> = catalogRepository.getTvShowOnTheAir()

}
package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.CatalogRepository
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.vo.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val catalogRepository: CatalogRepository)
    : ViewModel() {

    fun getListNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>> = catalogRepository.getNowPlayingMovies()

    fun getListOnTheAirTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = catalogRepository.getTvShowOnTheAir()

}
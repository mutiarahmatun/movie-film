package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.MovieFilmRepository
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val catalogRepository: MovieFilmRepository) : ViewModel() {

    fun getListFavoriteMovie(): LiveData<PagedList<MovieEntity>> = catalogRepository.getListFavoriteMovies()

    fun getListFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = catalogRepository.getListFavoriteTvShows()
}
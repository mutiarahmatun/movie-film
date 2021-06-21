package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.valueobject.ResourceData

interface MovieFilmDataSource {

    fun getNowPlayingMovies(): LiveData<ResourceData<PagedList<MovieEntity>>>

    fun getTvShowOnTheAir(): LiveData<ResourceData<PagedList<TvShowEntity>>>

    fun getListFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getListFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity>

    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity>

    fun setFavoriteMovie(movie: MovieEntity)

    fun setFavoriteTvShow(tvShow: TvShowEntity)
}
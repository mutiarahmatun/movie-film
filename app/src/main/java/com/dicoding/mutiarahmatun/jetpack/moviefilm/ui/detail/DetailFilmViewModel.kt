package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.MovieFilmRepository
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import javax.inject.Inject

class DetailFilmViewModel @Inject constructor(private val movieFilmRepository: MovieFilmRepository) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
            movieFilmRepository.getDetailMovie(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> =
            movieFilmRepository.getDetailTvShow(tvShowId)

    fun setFavoriteMovie(movie: MovieEntity){
        movieFilmRepository.setFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity){
        movieFilmRepository.setFavoriteTvShow(tvShow)
    }
}
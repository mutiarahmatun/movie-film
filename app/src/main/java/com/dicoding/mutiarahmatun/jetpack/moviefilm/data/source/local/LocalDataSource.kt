package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.room.MovieFilmDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieFilmDao: MovieFilmDao) {

    fun getAllMovies() : DataSource.Factory<Int, MovieEntity> = movieFilmDao.getListMovies()

    fun getAllFavoriteMovies() : DataSource.Factory<Int, MovieEntity> = movieFilmDao.getListFavoriteMovies()

    fun getDetailMovie(movieId: Int) : LiveData<MovieEntity> = movieFilmDao.getDetailMovieById(movieId)

    fun insertMovies(movies: List<MovieEntity>) = movieFilmDao.insertMovies(movies)

    fun setFavoriteMovie(movie : MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        movieFilmDao.updateMovie(movie)
    }

    fun getAllTvShows() : DataSource.Factory<Int, TvShowEntity> = movieFilmDao.getListTvShows()

    fun getAllFavoriteTvShows() : DataSource.Factory<Int, TvShowEntity> = movieFilmDao.getListFavoriteTvShows()

    fun getDetailTvShow(tvShowId: Int) : LiveData<TvShowEntity> = movieFilmDao.getDetailTvShowById(tvShowId)

    fun insertTvShows(tvShows: List<TvShowEntity>) = movieFilmDao.insertTvShows(tvShows)

    fun setFavoriteTvShow(tvShow : TvShowEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        movieFilmDao.updateTvShow(tvShow)
    }

}
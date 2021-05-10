package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.FilmEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.DataDummy

class DetailFilmViewModel : ViewModel() {

    private lateinit var tvShowId: String
    private lateinit var movieId: String

    private fun getListTvShow(): ArrayList<FilmEntity> =
        DataDummy.generateDummyTvShows() as ArrayList<FilmEntity>

    private fun getListMovie(): ArrayList<FilmEntity> =
        DataDummy.generateDummyMovies() as ArrayList<FilmEntity>

    fun setTvShowId(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun setMovieId(movieId: String) {
        this.movieId = movieId
    }

    fun getDetailTvShowById(): FilmEntity {
        lateinit var filmEntity: FilmEntity
        val listTvShow = getListTvShow()
        for (tvShow in listTvShow) {
            if (tvShow.id == tvShowId) {
                filmEntity = tvShow
                break
            }
        }
        return filmEntity
    }

    fun getDetailMovieById(): FilmEntity {
        lateinit var filmEntity: FilmEntity
        val listMovie = getListMovie()
        for (movie in listMovie) {
            if (movie.id == movieId) {
                filmEntity = movie
                break
            }
        }
        return  filmEntity
    }
}
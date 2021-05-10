package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail

import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailFilmViewModelTest {

    private lateinit var viewModel: DetailFilmViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel()
        viewModel.setMovieId(movieId)
        viewModel.setTvShowId(tvShowId)
    }

    @Test
    fun getDetailTvShowById() {
        viewModel.setTvShowId(dummyTvShow.id)
        val tvShowEntity = viewModel.getDetailTvShowById()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity.id)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
        assertEquals(dummyTvShow.releaseYear, tvShowEntity.releaseYear)
        assertEquals(dummyTvShow.description, tvShowEntity.description)
        assertEquals(dummyTvShow.genre, tvShowEntity.genre)
        assertEquals(dummyTvShow.imgBackground, tvShowEntity.imgBackground)
        assertEquals(dummyTvShow.imgPoster, tvShowEntity.imgPoster)
    }

    @Test
    fun getDetailMovieById() {
        viewModel.setMovieId(dummyMovie.id)
        val movieEntity = viewModel.getDetailMovieById()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.releaseYear, movieEntity.releaseYear)
        assertEquals(dummyMovie.description, movieEntity.description)
        assertEquals(dummyMovie.imgPoster, movieEntity.imgPoster)
        assertEquals(dummyMovie.imgBackground, movieEntity.imgBackground)
        assertEquals(dummyMovie.genre, movieEntity.genre)
    }
}
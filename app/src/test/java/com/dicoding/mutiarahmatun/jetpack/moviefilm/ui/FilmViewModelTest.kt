package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class FilmViewModelTest {

    private lateinit var viewModel: FilmViewModel

    @Before
    fun setUp() {
        viewModel = FilmViewModel()
    }

    @Test
    fun getListTvShow() {
        val tvShow = viewModel.getListTvShow()
        assertNotNull(tvShow)
        assertEquals(10, tvShow.size)
    }

    @Test
    fun getListMovie() {
        val movie = viewModel.getListMovie()
        assertNotNull(movie)
        assertEquals(10, movie.size)
    }
}
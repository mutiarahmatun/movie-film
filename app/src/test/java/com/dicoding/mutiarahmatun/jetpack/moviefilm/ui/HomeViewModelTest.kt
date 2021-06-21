package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.MovieFilmRepository
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.home.HomeViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.valueobject.ResourceData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: MovieFilmRepository

    @Mock
    private lateinit var observerMovie: Observer<ResourceData<PagedList<MovieEntity>>>

    @Mock
    private lateinit var observerTvShow: Observer<ResourceData<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(catalogRepository)
    }

    @Test
    fun getListNowPlayingMovies() {
        val dummyMovie = ResourceData.success(moviePagedList)
        `when`(dummyMovie.data?.size).thenReturn(5)
        val movie = MutableLiveData<ResourceData<PagedList<MovieEntity>>>()
        movie.value = dummyMovie

        `when`(catalogRepository.getNowPlayingMovies()).thenReturn(movie)
        val movieEntity = viewModel.getListNowPlayingMovies().value?.data
        verify(catalogRepository).getNowPlayingMovies()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getListNowPlayingMovies().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getListOnTheAirTvShows() {
        val dummyTvShow = ResourceData.success(tvShowPagedList)
        `when`(dummyTvShow.data?.size).thenReturn(5)

        val tvShow = MutableLiveData<ResourceData<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTvShow

        `when`(catalogRepository.getTvShowOnTheAir()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getListOnTheAirTvShows().value?.data
        verify(catalogRepository).getTvShowOnTheAir()
        assertNotNull(tvShowEntity)
        assertEquals(5, tvShowEntity?.size)

        viewModel.getListOnTheAirTvShows().observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(dummyTvShow)
    }
}
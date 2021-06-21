package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.MovieFilmRepository
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: MovieFilmRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerTvShow: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(catalogRepository)
    }

    @Test
    fun getListFavoriteMovie() {

        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovie

        `when`(catalogRepository.getListFavoriteMovies()).thenReturn(movie)
        val movieEntity = viewModel.getListFavoriteMovie().value
        verify(catalogRepository).getListFavoriteMovies()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getListFavoriteMovie().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getListFavoriteTvShow() {
        val dummyTvShow = tvShowPagedList
        `when`(dummyTvShow.size).thenReturn(5)
        val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(catalogRepository.getListFavoriteTvShows()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getListFavoriteTvShow().value
        verify(catalogRepository).getListFavoriteTvShows()
        assertNotNull(tvShowEntity)
        assertEquals(5, tvShowEntity?.size)

        viewModel.getListFavoriteTvShow().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }
}
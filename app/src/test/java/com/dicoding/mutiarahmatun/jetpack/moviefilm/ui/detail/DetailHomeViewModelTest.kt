package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.CatalogRepository
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailHomeViewModelTest {

    private lateinit var viewModel: DetailFilmViewModel
    private val dummyMovie = DataDummy.generateDataMovieDummy()[0]
    private val dummyTvShow = DataDummy.generateDataTvShowDummy()[0]
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTvShow.tvShowId

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTvShow: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel(catalogRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyMovie

        Mockito.`when`(catalogRepository.getMovieDetail(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.getMovieDetail(movieId).value

        assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData?.id)
        assertEquals(dummyMovie.movieId, movieData?.movieId)
        assertEquals(dummyMovie.title, movieData?.title)
        assertEquals(dummyMovie.description, movieData?.description)
        assertEquals(dummyMovie.releaseYear, movieData?.releaseYear)
        assertEquals(dummyMovie.imgPoster, movieData?.imgPoster)
        assertEquals(dummyMovie.imgBackground, movieData?.imgBackground)

        viewModel.getMovieDetail(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)

    }

    @Test
    fun getTvShowDetail() {
        val tvShowDummy = MutableLiveData<TvShowEntity>()
        tvShowDummy.value = dummyTvShow

        Mockito.`when`(catalogRepository.getTvShowDetail(tvShowId)).thenReturn(tvShowDummy)

        val tvShowData = viewModel.getTvShowDetail(tvShowId).value

        assertNotNull(tvShowData)
        assertEquals(dummyTvShow.id, tvShowData?.id)
        assertEquals(dummyTvShow.tvShowId, tvShowData?.tvShowId)
        assertEquals(dummyTvShow.title, tvShowData?.title)
        assertEquals(dummyTvShow.description, tvShowData?.description)
        assertEquals(dummyTvShow.releaseYear, tvShowData?.releaseYear)
        assertEquals(dummyTvShow.imgPoster, tvShowData?.imgPoster)
        assertEquals(dummyTvShow.imgBackground, tvShowData?.imgBackground)

        viewModel.getTvShowDetail(tvShowId).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }
}
package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.mutiarahmatun.jetpack.moviefilm.LiveDataTestUtils
import com.dicoding.mutiarahmatun.jetpack.moviefilm.PagedListUtil
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.FakeCatalogRepository
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.LocalDataSource
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.RemoteDataSource
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.DataDummy
import com.dicoding.mutiarahmatun.jetpack.moviefilm.vo.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote, local)

    private val listMovieResponse = DataDummy.generateDataMovieDummy()

    private val listTvShowResponse = DataDummy.generateDataTvShowDummy()

    private val movieResponse = DataDummy.generateDataMovieDummy()[0]
    private val tvShowResponse = DataDummy.generateDataTvShowDummy()[0]

    @Test
    fun getNowPlayingMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getListMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getNowPlayingMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovieDummy()))
        verify(local).getListMovies()
        assertNotNull(movieEntity.data)
        assertEquals(listMovieResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movieResponse
        `when`(local.getDetailMovie(movieResponse.movieId)).thenReturn(dummyMovie)

        val data = LiveDataTestUtils.getValue(catalogRepository.getMovieDetail(movieResponse.movieId))
        verify(local).getDetailMovie(movieResponse.movieId)
        assertNotNull(data)
        assertEquals(movieResponse.movieId, data.movieId)
    }

    @Test
    fun getTvShowOnTheAir() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getListTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getTvShowOnTheAir()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataTvShowDummy()))
        verify(local).getListTvShows()
        assertNotNull(tvShowEntity.data)
        assertEquals(listTvShowResponse.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = tvShowResponse
        `when`(local.getDetailTvShow(tvShowResponse.tvShowId)).thenReturn(dummyTvShow)

        val data = LiveDataTestUtils.getValue(catalogRepository.getTvShowDetail(tvShowResponse.tvShowId))
        verify(local).getDetailTvShow(tvShowResponse.tvShowId)
        assertNotNull(data)
        assertEquals(tvShowResponse.tvShowId, data.tvShowId)
    }

    @Test
    fun getListFavoriteMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getListFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getListFavoriteMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovieDummy()))
        verify(local).getListFavoriteMovies()
        assertNotNull(movieEntity.data)
        assertEquals(listMovieResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getListFavoriteTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getListFavoriteTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getListFavoriteTvShows()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataTvShowDummy()))
        verify(local).getListFavoriteTvShows()
        assertNotNull(tvShowEntity.data)
        assertEquals(listTvShowResponse.size.toLong(), tvShowEntity.data?.size?.toLong())
    }
}
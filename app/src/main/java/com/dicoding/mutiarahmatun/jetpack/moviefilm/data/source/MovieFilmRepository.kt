package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.LocalDataSource
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.RemoteDataSource
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.response.MovieResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.response.TvShowResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.valueobject.NetworkApiResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.AppThreadExecutors
import com.dicoding.mutiarahmatun.jetpack.moviefilm.valueobject.ResourceData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieFilmRepository @Inject constructor(

    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,

    ) : MovieFilmDataSource {

    private val appThreadExecutors: AppThreadExecutors = AppThreadExecutors()

    override fun getNowPlayingMovies(): LiveData<ResourceData<PagedList<MovieEntity>>> {

        return object : NetworkBoundDataResource<PagedList<MovieEntity>, List<MovieResponse>>(appThreadExecutors) {

            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {

                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()

                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<NetworkApiResponse<List<MovieResponse>>> =
                remoteDataSource.getNowPlayingMovies()

            public override fun saveCallResult(data: List<MovieResponse>) {

                val movieList = ArrayList<MovieEntity>()

                for (item in data) {
                    val movie = MovieEntity(
                        item.id,
                        item.title,
                        item.description,
                        item.releaseYear,
                        item.imgPoster,
                        item.imgBackground,
                        false
                    )

                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getTvShowOnTheAir(): LiveData<ResourceData<PagedList<TvShowEntity>>> {

        return object : NetworkBoundDataResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appThreadExecutors) {

            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {

                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()

                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<NetworkApiResponse<List<TvShowResponse>>> =
                    remoteDataSource.getTvShowOnTheAir()

            public override fun saveCallResult(data: List<TvShowResponse>) {

                val tvShowList = ArrayList<TvShowEntity>()

                for (item in data) {
                    val tvShow = TvShowEntity(
                            item.id,
                            item.title,
                            item.description,
                            item.releaseYear,
                            item.imgPoster,
                            item.imgBackground,
                            false
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShows(tvShowList)
            }

        }.asLiveData()
    }


    override fun getListFavoriteMovies(): LiveData<PagedList<MovieEntity>> {

        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()

        return LivePagedListBuilder(localDataSource.getAllFavoriteMovies(), config).build()
    }

    override fun getListFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {

        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()

        return LivePagedListBuilder(localDataSource.getAllFavoriteTvShows(), config).build()
    }

    override fun getDetailMovie(movieId: Int): LiveData<MovieEntity> =
        localDataSource.getDetailMovie(movieId)

    override fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity> =
        localDataSource.getDetailTvShow(tvShowId)

    override fun setFavoriteMovie(movie: MovieEntity) {
        appThreadExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie) }
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity) {
        appThreadExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow) }
    }

}
package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.FilmEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.RemoteDataSource
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.response.MovieResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CatalogRepository private constructor(private val remoteDataSource: RemoteDataSource) : CatalogDataSource {

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(remoteDataSource)
            }
    }

    override fun getNowPlayingMovies(): LiveData<List<FilmEntity>> {
        val listMovieResult = MutableLiveData<List<FilmEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getNowPlayingMovies(object : RemoteDataSource.LoadNowPlayingMoviesCallback{
                override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                    val movieList = ArrayList<FilmEntity>()
                    for (response in movieResponse){
                        val movie = FilmEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.releaseYear,
                            response.imgPoster,
                            response.imgBackground
                        )
                        movieList.add(movie)
                    }
                    listMovieResult.postValue(movieList)
                }
            })
        }

        return listMovieResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<FilmEntity> {
        val movieResult = MutableLiveData<FilmEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback{
                override fun onMovieDetailReceived(movieResponse: MovieResponse) {
                    val movie = FilmEntity(
                        movieResponse.id,
                        movieResponse.title,
                        movieResponse.description,
                        movieResponse.releaseYear,
                        movieResponse.imgPoster,
                        movieResponse.imgBackground
                    )

                    movieResult.postValue(movie)
                }
            })
        }

        return movieResult
    }

    override fun getTvShowOnTheAir(): LiveData<List<FilmEntity>> {
        val listTvShowResult = MutableLiveData<List<FilmEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShowOnTheAir(object : RemoteDataSource.LoadOnTheAirTvShowCallback{
                override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
                    val tvShowList = ArrayList<FilmEntity>()
                    for (response in tvShowResponse){
                        val tvShow = FilmEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.releaseYear,
                            response.imgPoster,
                            response.imgBackground
                        )
                        tvShowList.add(tvShow)
                    }

                    listTvShowResult.postValue(tvShowList)
                }
            })
        }

        return listTvShowResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<FilmEntity> {
        val tvShowResult = MutableLiveData<FilmEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShowDetail(tvShowId, object : RemoteDataSource.LoadTvShowDetailCallback{
                override fun onTvShowDetailReceived(tvShowResponse: TvShowResponse) {
                    val tvShow = FilmEntity(
                        tvShowResponse.id,
                        tvShowResponse.title,
                        tvShowResponse.description,
                        tvShowResponse.releaseYear,
                        tvShowResponse.imgPoster,
                        tvShowResponse.imgBackground
                    )

                    tvShowResult.postValue(tvShow)
                }
            })
        }

        return tvShowResult
    }
}
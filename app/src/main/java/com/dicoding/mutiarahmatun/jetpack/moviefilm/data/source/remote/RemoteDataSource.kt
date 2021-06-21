package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.api.ApiService
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.response.MovieResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.response.TvShowResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.valueobject.NetworkApiResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.await
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
        private val catalogApiService: ApiService) {

    fun getNowPlayingMovies(): LiveData<NetworkApiResponse<List<MovieResponse>>> {

        EspressoIdlingResource.increment()

        val resultPlayingMovieResponse = MutableLiveData<NetworkApiResponse<List<MovieResponse>>>()

        CoroutineScope(IO).launch {
            try {

                val response = catalogApiService.getNowPlayingMovies().await()
                resultPlayingMovieResponse.postValue(NetworkApiResponse.success(response.result!!))

            } catch (e: IOException) {

                e.printStackTrace()

                resultPlayingMovieResponse.postValue(

                    NetworkApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }

        EspressoIdlingResource.decrement()

        return resultPlayingMovieResponse
    }

    fun getTvShowOnTheAir(): LiveData<NetworkApiResponse<List<TvShowResponse>>> {

        EspressoIdlingResource.increment()

        val resultOnAirTvShowResponse = MutableLiveData<NetworkApiResponse<List<TvShowResponse>>>()

        CoroutineScope(IO).launch {
            try {

                val response = catalogApiService.getTvShowOnTheAir().await()
                resultOnAirTvShowResponse.postValue(NetworkApiResponse.success(response.result!!))

            } catch (e: IOException) {

                e.printStackTrace()

                resultOnAirTvShowResponse.postValue(

                    NetworkApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }

        EspressoIdlingResource.decrement()

        return resultOnAirTvShowResponse
    }

}
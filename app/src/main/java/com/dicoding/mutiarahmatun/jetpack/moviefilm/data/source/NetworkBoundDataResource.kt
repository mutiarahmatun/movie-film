package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.valueobject.NetworkApiResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.valueobject.StatusNetworkResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.AppThreadExecutors
import com.dicoding.mutiarahmatun.jetpack.moviefilm.valueobject.ResourceData

abstract class NetworkBoundDataResource<ResultType, RequestType>
    (private val mExecutors: AppThreadExecutors) {

    private val result = MediatorLiveData<ResourceData<ResultType>>()

    init {
        result.value = ResourceData.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->

            result.removeSource(dbSource)

            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = ResourceData.success(newData)
                }
            }
        }
    }

    private fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<NetworkApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = ResourceData.loading(newData)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response.statusNetwork) {
                StatusNetworkResponse.SUCCESS ->
                    mExecutors.diskIO().execute {
                        saveCallResult(response.body)
                        mExecutors.mainThread().execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = ResourceData.success(newData)
                            }
                        }
                    }

                StatusNetworkResponse.ERROR -> {
                    onFetchFailed()

                    result.addSource(dbSource) { newData ->
                        result.value = ResourceData.error(response.message, newData)
                    }
                }

                StatusNetworkResponse.EMPTY -> mExecutors.mainThread().execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = ResourceData.success(newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<ResourceData<ResultType>> = result
}
package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.valueobject.ApiResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.valueobject.StatusResponse
import com.dicoding.mutiarahmatun.jetpack.moviefilm.valueobject.ResourceData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundDataResource<ResultType, RequestType> {

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

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = ResourceData.loading(newData)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response.status) {
                StatusResponse.SUCCESS ->
                    CoroutineScope(IO).launch {
                        response.body?.let { saveCallResult(it) }

                        withContext(Main) {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = ResourceData.success(newData)
                            }
                        }

                    }

                StatusResponse.ERROR -> {
                    onFetchFailed()

                    result.addSource(dbSource) { newData ->
                        result.value = ResourceData.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<ResourceData<ResultType>> = result
}
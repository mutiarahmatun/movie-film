package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.valueobject

class NetworkApiResponse<T>(val statusNetwork: StatusNetworkResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): NetworkApiResponse<T> = NetworkApiResponse(StatusNetworkResponse.SUCCESS, body, null)

        fun <T> error(msg: String, body: T): NetworkApiResponse<T> = NetworkApiResponse(StatusNetworkResponse.ERROR, body, msg)
    }
}
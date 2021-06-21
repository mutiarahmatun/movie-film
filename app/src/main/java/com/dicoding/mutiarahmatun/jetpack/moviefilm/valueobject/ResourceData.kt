package com.dicoding.mutiarahmatun.jetpack.moviefilm.valueobject

data class ResourceData<T>(val indicatorStatus: IndicatorStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ResourceData<T> = ResourceData(IndicatorStatus.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): ResourceData<T> = ResourceData(IndicatorStatus.ERROR, data, msg)

        fun <T> loading(data: T?): ResourceData<T> = ResourceData(IndicatorStatus.LOADING, data, null)
    }
}

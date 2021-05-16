package com.dicoding.mutiarahmatun.jetpack.moviefilm.di

import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.CatalogRepository
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.RemoteDataSource

object Injection {

    fun provideCatalogRepository(): CatalogRepository {

        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogRepository.getInstance(remoteDataSource)
    }
}
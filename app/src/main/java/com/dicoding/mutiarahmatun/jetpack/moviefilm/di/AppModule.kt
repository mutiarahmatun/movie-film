package com.dicoding.mutiarahmatun.jetpack.moviefilm.di

import android.app.Application
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.MovieFilmRepository
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.LocalDataSource
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.room.MovieFilmDao
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.room.MovieFilmBuilderDatabase
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.RemoteDataSource
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.api.ApiService
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.AppThreadExecutors
import com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideMovieFilmBuilderDatabase(application: Application): MovieFilmBuilderDatabase =
            MovieFilmBuilderDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideMovieFilmDao(movieFilmBuilderDatabase: MovieFilmBuilderDatabase): MovieFilmDao =
            movieFilmBuilderDatabase.movieFilmDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(movieFilmDao: MovieFilmDao): LocalDataSource =
            LocalDataSource(movieFilmDao)

        @Singleton
        @Provides
        fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
            RemoteDataSource(apiService)

        @Singleton
        @Provides
        fun provideMovieFilmRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): MovieFilmRepository = MovieFilmRepository(remoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(catalogRepository: MovieFilmRepository): ViewModelFactory =
            ViewModelFactory(catalogRepository)

    }
}
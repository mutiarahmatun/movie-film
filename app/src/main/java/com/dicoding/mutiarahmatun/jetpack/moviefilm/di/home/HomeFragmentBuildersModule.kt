package com.dicoding.mutiarahmatun.jetpack.moviefilm.di.home

import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.movie.MovieFragment
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.tv_show.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment() : MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment() : TvShowFragment

    @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
    abstract fun contributeFavoriteFragment() : FavoriteFragment
}
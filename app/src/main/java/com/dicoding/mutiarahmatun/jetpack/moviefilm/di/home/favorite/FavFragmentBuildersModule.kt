package com.dicoding.mutiarahmatun.jetpack.moviefilm.di.home.favorite

import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite.movie.FavMovieFragment
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite.tv_show.FavTvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFavoriteMovieFragment() : FavMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteTvShowFragment() : FavTvShowFragment
}
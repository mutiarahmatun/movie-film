package com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.MovieFilmRepository
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail.DetailFilmViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite.FavoriteViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.home.HomeViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
        private val mCatalogRepository: MovieFilmRepository):
        ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mCatalogRepository) as T
            }

            modelClass.isAssignableFrom(DetailFilmViewModel::class.java) -> {
                DetailFilmViewModel(mCatalogRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mCatalogRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}
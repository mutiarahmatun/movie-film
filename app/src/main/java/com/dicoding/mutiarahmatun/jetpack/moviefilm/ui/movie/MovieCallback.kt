package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.movie

import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity

interface MovieCallback {
    fun onItemClicked(data: MovieEntity)
}
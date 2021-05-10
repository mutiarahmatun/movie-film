package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui

import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.FilmEntity

interface FilmCallback {
    fun onItemClicked(data: FilmEntity)
}
package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.tv_show

import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity

interface TvShowCallback {
    fun onItemClicked(tvShowEntity: TvShowEntity)
}
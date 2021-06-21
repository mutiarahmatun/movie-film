package com.dicoding.mutiarahmatun.jetpack.moviefilm.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mutiarahmatun.jetpack.moviefilm.R

object ObjectFilmHelper {

    const val TYPE_MOVIE = "TYPE_MOVIE"
    const val TYPE_TV_SHOW = "TYPE_TV_SHOW"
    const val ENDPOINT_POSTER_SIZE_W185 = "w185"
    const val ENDPOINT_POSTER_SIZE_W780 = "w780"

    fun ImageView.loadFromUrl(path: String) {
        Glide.with(this).clear(this)
        Glide.with(this)
                .setDefaultRequestOptions(
                        RequestOptions()
                                .placeholder(R.drawable.logo_mf)
                                .error(R.drawable.logo_mf)
                ).load(path).into(this)
    }
}
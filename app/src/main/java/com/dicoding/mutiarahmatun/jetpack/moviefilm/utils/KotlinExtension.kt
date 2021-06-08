package com.dicoding.mutiarahmatun.jetpack.moviefilm.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mutiarahmatun.jetpack.moviefilm.R

fun ImageView.loadFromUrl(path: String) {
    Glide.with(this).clear(this)
    Glide.with(this)
        .setDefaultRequestOptions(
            RequestOptions()
            .placeholder(R.drawable.ic_movie)
            .error(R.drawable.ic_movie)
        ).load(path).into(this)
}
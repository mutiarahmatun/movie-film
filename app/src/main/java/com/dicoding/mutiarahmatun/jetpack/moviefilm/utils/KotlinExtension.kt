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
            .placeholder(R.drawable.logo_mf)
            .error(R.drawable.logo_mf)
        ).load(path).into(this)
}
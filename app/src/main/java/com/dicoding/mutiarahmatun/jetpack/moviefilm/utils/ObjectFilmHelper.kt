package com.dicoding.mutiarahmatun.jetpack.moviefilm.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ObjectFilmHelper {
    const val TYPE_MOVIE = "TYPE_MOVIE"
    const val TYPE_TV_SHOW = "TYPE_TV_SHOW"

    fun setGlideImage(context: Context, imagePath: Int, imageView: ImageView) {
        Glide.with(context).clear(imageView)
        Glide.with(context).load(imagePath).into(imageView)
    }
}
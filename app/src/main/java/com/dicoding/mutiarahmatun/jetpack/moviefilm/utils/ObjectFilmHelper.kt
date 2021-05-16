package com.dicoding.mutiarahmatun.jetpack.moviefilm.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ObjectFilmHelper {

    const val TYPE_MOVIE = "TYPE_MOVIE"
    const val TYPE_TV_SHOW = "TYPE_TV_SHOW"
    const val API_IMAGE_ENDPOINT = "https://image.tmdb.org/t/p/"
    const val ENDPOINT_POSTER_SIZE_W185 = "w185"
    const val ENDPOINT_POSTER_SIZE_W780 = "w780"

    fun setGlideImage(context: Context, imagePath: String, imageView: ImageView) {
        Glide.with(context).clear(imageView)
        Glide.with(context).load(imagePath).into(imageView)
    }
}
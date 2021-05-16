package com.dicoding.mutiarahmatun.jetpack.moviefilm.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmEntity(
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var releaseYear: String? = null,
    var imgPoster: String? = null,
    var imgBackground: String? = null
)   : Parcelable

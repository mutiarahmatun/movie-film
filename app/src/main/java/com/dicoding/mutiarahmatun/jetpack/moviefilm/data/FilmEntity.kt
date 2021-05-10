package com.dicoding.mutiarahmatun.jetpack.moviefilm.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmEntity(
    var id: String,
    var title: String,
    var description: String,
    var genre: String,
    var releaseYear: String,
    var imgPoster: Int,
    var imgBackground: Int
)   : Parcelable

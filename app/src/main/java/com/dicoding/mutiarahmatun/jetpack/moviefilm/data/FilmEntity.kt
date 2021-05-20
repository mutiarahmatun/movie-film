package com.dicoding.mutiarahmatun.jetpack.moviefilm.data

data class FilmEntity(
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var releaseYear: String? = null,
    var imgPoster: String? = null,
    var imgBackground: String? = null
)

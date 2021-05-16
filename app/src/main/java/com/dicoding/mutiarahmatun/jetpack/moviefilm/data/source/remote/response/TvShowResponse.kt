package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var title: String? = null,
    @SerializedName("overview")
    var description: String? = null,
    @SerializedName("first_air_date")
    var releaseYear: String? = null,
    @SerializedName("poster_path")
    var imgPoster: String? = null,
    @SerializedName("backdrop_path")
    var imgBackground: String? = null
) : Parcelable

package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_favorite_movie")
@Parcelize
data class MovieEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null,

        @NonNull
        @ColumnInfo(name = "movieId")
        var movieId: Int = 0,

        @ColumnInfo(name = "movieName")
        var title: String? = null,

        @ColumnInfo(name = "movieDesc")
        var description: String? = null,

        @ColumnInfo(name = "movieReleaseYear")
        var releaseYear: String? = null,

        @ColumnInfo(name = "moviePoster")
        var imgPoster: String? = null,

        @ColumnInfo(name = "movieImgPreview")
        var imgBackground: String? = null,

        @NonNull
        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
): Parcelable
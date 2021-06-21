package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "db_favorite_tv_show")
@Parcelize
data class TvShowEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int = 0,

    @ColumnInfo(name = "tvShowName")
    var title: String? = null,

    @ColumnInfo(name = "tvShowDesc")
    var description: String? = null,

    @ColumnInfo(name = "tvShowReleaseYear")
    var releaseYear: String? = null,

    @ColumnInfo(name = "tvShowPoster")
    var imgPoster: String? = null,

    @ColumnInfo(name = "tvShowImgPreview")
    var imgBackground: String? = null,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable
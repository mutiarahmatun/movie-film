package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class],
        version = 1, exportSchema = false)

abstract class MovieFilmBuilderDatabase : RoomDatabase() {
    abstract fun movieFilmDao(): MovieFilmDao

    companion object {

        @Volatile
        private var INSTANCE: MovieFilmBuilderDatabase? = null

        fun getInstance(context: Context): MovieFilmBuilderDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieFilmBuilderDatabase::class.java,
                    "MovieFilm.db"
                ).build()
            }
    }
}
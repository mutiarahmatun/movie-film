package com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.paging.DataSource
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity

@Dao
interface CatalogDao {

    @Query("SELECT * FROM tb_favorite_movie")
    fun getListMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tb_favorite_tv_show")
    fun getListTvShows() : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tb_favorite_movie WHERE isFavorite = 1")
    fun getListFavoriteMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tb_favorite_tv_show WHERE isFavorite = 1")
    fun getListFavoriteTvShows() : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tb_favorite_movie WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int) : LiveData<MovieEntity>

    @Query("SELECT * FROM tb_favorite_tv_show WHERE tvShowId = :tvShowId")
    fun getDetailTvShowById(tvShowId: Int) : LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update(entity = MovieEntity::class)
    fun updateMovie(movie : MovieEntity)

    @Update(entity = TvShowEntity::class)
    fun updateTvShow(tvShows: TvShowEntity)
}
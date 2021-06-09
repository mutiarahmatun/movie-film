package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.mutiarahmatun.jetpack.moviefilm.BuildConfig
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.ItemMovieFilmBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.Constants
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.loadFromUrl

class MovieAdapter (private val callback: MovieCallback) :
    PagedListAdapter<MovieEntity, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val viewBinding = ItemMovieFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class ListViewHolder(private val viewBinding: ItemMovieFilmBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: MovieEntity) {
            with(viewBinding) {
                data.poster?.let {
                    imgItemPhoto.loadFromUrl(BuildConfig.BASE_URL_IMAGE_TMDB + Constants.ENDPOINT_POSTER_SIZE_W185 + it)
                }
                tvTitle.text = data.name

                itemFilm.setOnClickListener {
                    callback.onItemClicked(data)
                }

            }

        }
    }


}
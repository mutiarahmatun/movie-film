package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.mutiarahmatun.jetpack.moviefilm.BuildConfig
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.ItemMovieFilmBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.Constants
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.loadFromUrl

class TvShowAdapter (private val callback: TvShowCallback) :
    PagedListAdapter<TvShowEntity, TvShowAdapter.ListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val viewBinding = ItemMovieFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class ListViewHolder (private val viewBinding: ItemMovieFilmBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(tvShowEntity: TvShowEntity) {
            with(viewBinding) {
                tvTitle.text = tvShowEntity.title

                tvShowEntity.imgPoster?.let {
                    imgItemPhoto.loadFromUrl(BuildConfig.BASE_URL_IMAGE_TMDB + Constants.ENDPOINT_POSTER_SIZE_W185 + it)
                }

                itemFilm.setOnClickListener { callback?.onItemClicked(tvShowEntity) }
            }
        }
    }

}
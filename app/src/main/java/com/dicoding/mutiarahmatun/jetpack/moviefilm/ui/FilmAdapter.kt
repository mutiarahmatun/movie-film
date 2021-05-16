package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.ItemMovieFilmBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.FilmEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.API_IMAGE_ENDPOINT
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.ENDPOINT_POSTER_SIZE_W185
import java.util.ArrayList

class FilmAdapter (private val callback: FilmCallback) :
    RecyclerView.Adapter<FilmAdapter.ListViewHolder>() {

    private val listFilmEntity = ArrayList<FilmEntity>()

    fun setData(filmEntity: List<FilmEntity>?) {
        if (filmEntity == null) return
        listFilmEntity.clear()
        listFilmEntity.addAll(filmEntity)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val viewBinding = ItemMovieFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listFilmEntity[position])
    }

    override fun getItemCount(): Int {
        return listFilmEntity.size
    }

    inner class ListViewHolder (private val viewBinding: ItemMovieFilmBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(filmEntity: FilmEntity) {
            with(viewBinding) {
                tvTitle.text = filmEntity.title

                filmEntity.imgPoster?.let {
                    Glide.with(itemView.context)
                            .load(API_IMAGE_ENDPOINT+ ENDPOINT_POSTER_SIZE_W185 + filmEntity.imgPoster)
                            .into(imgItemPhoto)
                }

                itemView.setOnClickListener { callback?.onItemClicked(filmEntity) }
            }
        }
    }

}
package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mutiarahmatun.jetpack.moviefilm.R
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.FragmentFavMovieBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail.DetailFilmActivity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite.FavoriteViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.movie.MovieAdapter
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.movie.MovieCallback
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.Constants
import com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavMovieFragment : DaggerFragment(), MovieCallback {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var binding: FragmentFavMovieBinding

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentFavMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()

        parentFragment?.let {
            favoriteViewModel = ViewModelProvider(it, factory)[FavoriteViewModel::class.java]
        }
        observeFavoriteMovies()

    }

    private fun observeFavoriteMovies() {
        favoriteViewModel.getListFavoriteMovie().observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.rvFavMovie.adapter?.let {adapter ->
                    when (adapter) {
                        is MovieAdapter -> {
                            if (it.isNullOrEmpty()){
                                binding.rvFavMovie.visibility = GONE
                                enableEmptyStateEmptyFavoriteMovie()
                            } else {
                                binding.rvFavMovie.visibility = VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }
    private fun setupRecyclerView() {
        binding.rvFavMovie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(this@FavMovieFragment)
        }
    }

    private fun enableEmptyStateEmptyFavoriteMovie() {

        binding.favMovieEmptyState.imgEmptyState.setImageResource(R.drawable.ic_empty_state_favorite)
        binding.favMovieEmptyState.imgEmptyState.contentDescription =
            resources.getString(R.string.empty_favorite_movie_list)
        binding.favMovieEmptyState.titleEmptyState.text = resources.getString(R.string.empty_favorite)
        binding.favMovieEmptyState.descEmptyState.text =
            resources.getString(R.string.empty_favorite_movie_list)
        binding.favMovieEmptyState.emptyState.visibility = VISIBLE
    }

    override fun onItemClicked(data: MovieEntity) {
        startActivity(
            Intent(
                context,
                DetailFilmActivity::class.java
            )
                .putExtra(DetailFilmActivity.EXTRA_DATA, data.movieId)
                .putExtra(DetailFilmActivity.EXTRA_TYPE, Constants.TYPE_MOVIE)
        )
    }

}
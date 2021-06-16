package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
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
    private lateinit var favMovieBinding: FragmentFavMovieBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        favMovieBinding = FragmentFavMovieBinding.inflate(inflater, container, false)
        return favMovieBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()

        parentFragment?.let {
            favoriteViewModel = ViewModelProvider(it, viewModelFactory)[FavoriteViewModel::class.java]
        }
        observeFavoriteMovies()

    }

    private fun observeFavoriteMovies() {
        favoriteViewModel.getListFavoriteMovie().observe(viewLifecycleOwner, Observer {
            if (it != null){
                favMovieBinding.rvFavMovie.adapter?.let { adapter ->
                    when (adapter) {
                        is MovieAdapter -> {
                            if (it.isNullOrEmpty()){
                                favMovieBinding.rvFavMovie.visibility = GONE
                                enableEmptyStateEmptyFavoriteMovie()
                            } else {
                                favMovieBinding.rvFavMovie.visibility = VISIBLE
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
        favMovieBinding.rvFavMovie.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MovieAdapter(this@FavMovieFragment)
        }
    }

    private fun enableEmptyStateEmptyFavoriteMovie() {

        favMovieBinding.favMovieEmptyState.imgEmptyState.setImageResource(R.drawable.ic_empty_state_favorite)
        favMovieBinding.favMovieEmptyState.imgEmptyState.contentDescription =
            resources.getString(R.string.empty_favorite_movie_list)
        favMovieBinding.favMovieEmptyState.titleEmptyState.text = resources.getString(R.string.empty_favorite)
        favMovieBinding.favMovieEmptyState.descEmptyState.text =
            resources.getString(R.string.empty_favorite_movie_list)
        favMovieBinding.favMovieEmptyState.emptyState.visibility = VISIBLE
    }

    override fun onItemClicked(movieEntity: MovieEntity) {
        startActivity(
            Intent(
                context,
                DetailFilmActivity::class.java
            )
                .putExtra(DetailFilmActivity.EXTRA_DATA, movieEntity.movieId)
                .putExtra(DetailFilmActivity.EXTRA_TYPE, Constants.TYPE_MOVIE)
        )
    }

}
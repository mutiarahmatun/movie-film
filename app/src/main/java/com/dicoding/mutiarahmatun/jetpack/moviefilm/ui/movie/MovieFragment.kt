package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.FragmentMovieBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.home.HomeViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail.DetailFilmActivity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.Constants.TYPE_MOVIE
import com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel.ViewModelFactory
import com.dicoding.mutiarahmatun.jetpack.moviefilm.vo.Status
import dagger.android.support.DaggerFragment

class MovieFragment : DaggerFragment(), MovieCallback {

    private lateinit var movieBinding: FragmentMovieBinding
    private lateinit var viewModelHome: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        movieBinding = FragmentMovieBinding.inflate(inflater, container, false)
        return movieBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecycler()

        val factory = ViewModelFactory.getInstance()
        activity?.let {
            viewModelHome = ViewModelProvider(
                it,
                factory
            )[HomeViewModel::class.java]
        }

        viewModelHome.getListNowPlayingMovies().observe(viewLifecycleOwner, Observer { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> movieBinding.progressBar.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        movieBinding.progressBar.progressBar.visibility = View.GONE
                        movieBinding.rvMovie.adapter?.let { adapter ->
                            when (adapter) {
                                is MovieAdapter -> {
                                    adapter.submitList(listMovie.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        movieBinding.progressBar.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }

    private fun setRecycler() {
        movieBinding.rvMovie.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MovieAdapter(this@MovieFragment)
        }
    }

    override fun onItemClicked(data: MovieEntity) {
        startActivity(
                Intent(context, DetailFilmActivity::class.java)
                        .putExtra(DetailFilmActivity.EXTRA_DATA, data.id)
                        .putExtra(DetailFilmActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }
}
package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.FragmentMovieBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.FilmAdapter
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.FilmCallback
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.FilmViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail.DetailFilmActivity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.TYPE_MOVIE
import com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel.ViewModelFactory

class MovieFragment : Fragment(), FilmCallback {

    private lateinit var movieBinding: FragmentMovieBinding
    private lateinit var viewModelFilm: FilmViewModel

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
            viewModelFilm = ViewModelProvider(
                it,
                factory
            )[FilmViewModel::class.java]
        }

        viewModelFilm.getListNowPlayingMovies().observe(viewLifecycleOwner, Observer { listMovie ->
            movieBinding.rvMovie.adapter?.let { adapter ->
                when (adapter) {
                    is FilmAdapter -> adapter.setData(listMovie)
                }
            }
        })

    }

    private fun setRecycler() {
        movieBinding.rvMovie.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = FilmAdapter(this@MovieFragment)
        }
    }

    override fun onItemClicked(film: FilmEntity) {
        startActivity(
                Intent(context, DetailFilmActivity::class.java)
                        .putExtra(DetailFilmActivity.EXTRA_DATA, film.id)
                        .putExtra(DetailFilmActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }
}
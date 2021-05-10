package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.tv_show

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.mutiarahmatun.jetpack.moviefilm.R
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.TYPE_TV_SHOW
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail.DetailFilmActivity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.FilmEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.FragmentTvShowBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.FilmAdapter
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.FilmCallback
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.FilmViewModel

class TvShowFragment : Fragment(), FilmCallback {

    private lateinit var tvShowBinding: FragmentTvShowBinding
    private lateinit var viewModelFilm: FilmViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        tvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false);
        return tvShowBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModelFilm = ViewModelProvider(it, ViewModelProvider.NewInstanceFactory())[FilmViewModel::class.java]
        }

        val listTvShow = viewModelFilm.getListTvShow()
        setRecycler(listTvShow)
    }

    private fun setRecycler(data: List<FilmEntity>) {
        tvShowBinding.rvTvShow.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = FilmAdapter(this@TvShowFragment)
        }.also {
            it.adapter.let { adapter ->
                when (adapter) {
                    is FilmAdapter -> {
                        adapter.setData(data)
                    }
                }
            }
        }
    }

    override fun onItemClicked(film: FilmEntity) {
        startActivity(
            Intent(context, DetailFilmActivity::class.java)
                .putExtra(DetailFilmActivity.EXTRA_DATA, film.id)
                .putExtra(DetailFilmActivity.EXTRA_TYPE, TYPE_TV_SHOW)
        )
    }

}
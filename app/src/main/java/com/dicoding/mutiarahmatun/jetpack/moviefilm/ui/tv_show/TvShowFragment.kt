package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.tv_show

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.FragmentTvShowBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail.DetailFilmActivity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.home.HomeViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.Constants.TYPE_TV_SHOW
import com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel.ViewModelFactory
import com.dicoding.mutiarahmatun.jetpack.moviefilm.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TvShowFragment : DaggerFragment(), TvShowCallback {

    private lateinit var tvShowBinding: FragmentTvShowBinding
    private lateinit var viewModelHome: HomeViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        tvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false);
        return tvShowBinding.root
    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        fragmentActivity.let {
            viewModelHome = ViewModelProvider(
                    it,
                    factory
            )[HomeViewModel::class.java]
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecycler()


        activity?.let {
            setupViewModel(it)
        }

        viewModelHome.getListOnTheAirTvShows().observe(viewLifecycleOwner, { listTvShow ->
            if (listTvShow != null) {
                when (listTvShow.status) {
                    Status.LOADING -> tvShowBinding.progressBar.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        tvShowBinding.progressBar.progressBar.visibility = View.GONE
                        tvShowBinding.rvTvShow.adapter?.let { adapter ->
                            when (adapter) {
                                is TvShowAdapter -> {
                                    adapter.submitList(listTvShow.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        tvShowBinding.progressBar.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setRecycler() {
        tvShowBinding.rvTvShow.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = TvShowAdapter(this@TvShowFragment)
        }
    }

    override fun onItemClicked(tvShowEntity: TvShowEntity) {
        startActivity(
            Intent(context, DetailFilmActivity::class.java)
                .putExtra(DetailFilmActivity.EXTRA_DATA, tvShowEntity.tvShowId)
                .putExtra(DetailFilmActivity.EXTRA_TYPE, TYPE_TV_SHOW)
        )
    }

}
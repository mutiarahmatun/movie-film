package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite.tv_show

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
import com.dicoding.mutiarahmatun.jetpack.moviefilm.R
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.FragmentFavTvShowBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail.DetailFilmActivity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite.FavoriteViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.tv_show.TvShowAdapter
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.tv_show.TvShowCallback
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper
import com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavTvShowFragment : DaggerFragment(), TvShowCallback {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var tvShowBinding: FragmentFavTvShowBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        tvShowBinding = FragmentFavTvShowBinding.inflate(inflater, container, false)
        return tvShowBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()

        parentFragment?.let {
            favoriteViewModel = ViewModelProvider(it, viewModelFactory)[FavoriteViewModel::class.java]
        }
        observeFavoriteTvShow()

    }

    private fun observeFavoriteTvShow() {
        favoriteViewModel.getListFavoriteTvShow().observe(viewLifecycleOwner, Observer {
            if (it != null){
                tvShowBinding.rvFavTvShow.adapter?.let { adapter ->
                    when (adapter) {
                        is TvShowAdapter -> {
                            if (it.isNullOrEmpty()){
                                tvShowBinding.rvFavTvShow.visibility = GONE
                                enableEmptyStateEmptyFavoriteTvShow()
                            } else {
                                tvShowBinding.rvFavTvShow.visibility = VISIBLE
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
        tvShowBinding.rvFavTvShow.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = TvShowAdapter(this@FavTvShowFragment)
        }
    }

    private fun enableEmptyStateEmptyFavoriteTvShow() {
        tvShowBinding.favTvShowEmptyState.imgEmptyState.setImageResource(R.drawable.ic_empty_state_favorite)
        tvShowBinding.favTvShowEmptyState.imgEmptyState.contentDescription =
            resources.getString(R.string.empty_favorite_tv_show_list)
        tvShowBinding.favTvShowEmptyState.titleEmptyState.text = resources.getString(R.string.empty_favorite)
        tvShowBinding.favTvShowEmptyState.descEmptyState.text =
            resources.getString(R.string.empty_favorite_tv_show_list)
        tvShowBinding.favTvShowEmptyState.emptyState.visibility = VISIBLE
    }

    override fun onItemClicked(tvShowEntity: TvShowEntity) {
        startActivity(
            Intent(
                context,
                DetailFilmActivity::class.java
            )
                .putExtra(DetailFilmActivity.EXTRA_DATA, tvShowEntity.tvShowId)
                .putExtra(DetailFilmActivity.EXTRA_TYPE, ObjectFilmHelper.TYPE_TV_SHOW)
        )
    }

}
package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite.tv_show

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mutiarahmatun.jetpack.moviefilm.R
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.FragmentFavTvShowBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail.DetailFilmActivity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite.FavoriteViewModel
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.tv_show.TvShowAdapter
import com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.tv_show.TvShowCallback
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.Constants
import com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavTvShowFragment : DaggerFragment(), TvShowCallback {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var binding: FragmentFavTvShowBinding

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentFavTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()

        parentFragment?.let {
            favoriteViewModel = ViewModelProvider(it, factory)[FavoriteViewModel::class.java]
        }
        observeFavoriteTvShow()

    }

    private fun observeFavoriteTvShow() {
        favoriteViewModel.getListFavoriteTvShow().observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.rvFavTvShow.adapter?.let {adapter ->
                    when (adapter) {
                        is TvShowAdapter -> {
                            if (it.isNullOrEmpty()){
                                binding.rvFavTvShow.visibility = GONE
                                enableEmptyStateEmptyFavoriteTvShow()
                            } else {
                                binding.rvFavTvShow.visibility = VISIBLE
                                binding.rvFavTvShow.adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvFavTvShow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TvShowAdapter(this@FavTvShowFragment)
        }
    }

    private fun enableEmptyStateEmptyFavoriteTvShow() {
        binding.favTvShowEmptyState.imgEmptyState.setImageResource(R.drawable.ic_empty_state_favorite)
        binding.favTvShowEmptyState.imgEmptyState.contentDescription =
            resources.getString(R.string.empty_favorite_tv_show_list)
        binding.favTvShowEmptyState.titleEmptyState.text = resources.getString(R.string.empty_favorite)
        binding.favTvShowEmptyState.descEmptyState.text =
            resources.getString(R.string.empty_favorite_tv_show_list)
        binding.favTvShowEmptyState.visibility = VISIBLE
    }

    override fun onItemClicked(data: TvShowEntity) {
        startActivity(
            Intent(
                context,
                DetailFilmActivity::class.java
            )
                .putExtra(DetailFilmActivity.EXTRA_DATA, data.tvShowId)
                .putExtra(DetailFilmActivity.EXTRA_TYPE, Constants.TYPE_TV_SHOW)
        )
    }

}
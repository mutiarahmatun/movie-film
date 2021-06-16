package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.FragmentFavoriteBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class FavoriteFragment : DaggerFragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var favoriteBinding: FragmentFavoriteBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return favoriteBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let { setupViewPager(it) }
        viewModel = ViewModelProvider(this@FavoriteFragment, viewModelFactory)[FavoriteViewModel::class.java]
    }

    private fun setupViewPager(context: Context) {
        val sectionPagerAdapter = SectionPagerAdapter(context, childFragmentManager)
        favoriteBinding.favoriteViewPager.adapter = sectionPagerAdapter
        favoriteBinding.favoriteTabLayout.setupWithViewPager(favoriteBinding.favoriteViewPager)
    }
}
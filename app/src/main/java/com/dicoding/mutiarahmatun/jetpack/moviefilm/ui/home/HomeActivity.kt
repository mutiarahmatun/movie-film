package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.mutiarahmatun.jetpack.moviefilm.R
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.ActivityHomeBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity

class HomeActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(
            this@HomeActivity,
            factory
        )[HomeViewModel::class.java]

        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        supportActionBar?.elevation = 0f

        setupNavigationController()
    }

    private fun setupNavigationController() {
        val navView: BottomNavigationView = binding.bottomNavbar
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_movie,
                R.id.navigation_tv_show,
                R.id.navigation_favorite
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
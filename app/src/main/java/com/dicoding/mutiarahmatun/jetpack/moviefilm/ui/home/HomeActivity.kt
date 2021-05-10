package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.dicoding.mutiarahmatun.jetpack.moviefilm.R
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.synnapps.carouselview.ImageListener

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.carouselView.pageCount = carouselImages.size
        binding.carouselView.setImageListener(imageListener)

        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        supportActionBar?.elevation = 0f

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    private val carouselImages = intArrayOf(
            R.drawable.latar_a_star,
            R.drawable.latar_alita,
            R.drawable.latar_simpsons,
            R.drawable.latar_spider,
            R.drawable.latar_bohemian,
            R.drawable.latar_gotham
    )

    private val imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(carouselImages[position])
    }
}
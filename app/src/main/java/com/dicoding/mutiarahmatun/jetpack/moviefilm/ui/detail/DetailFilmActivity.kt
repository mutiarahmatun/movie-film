package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mutiarahmatun.jetpack.moviefilm.R
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.ActivityDetailFilmBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.API_IMAGE_ENDPOINT
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.ENDPOINT_POSTER_SIZE_W185
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.ENDPOINT_POSTER_SIZE_W780
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.TYPE_MOVIE
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.TYPE_TV_SHOW
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.setGlideImage
import com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailFilmActivity : DaggerAppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailFilmBinding: ActivityDetailFilmBinding
    private lateinit var viewModel: DetailFilmViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(detailFilmBinding.root)

        supportActionBar?.title = "Detail Film"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            viewModel.getMovieDetail(id).observe(this, Observer {
                displayData(it)
            })
        } else if (type.equals(TYPE_TV_SHOW, ignoreCase = true)) {
            viewModel.getTvShowDetail(id).observe(this, Observer {
                displayData(it)
            })
        }
    }

    private fun displayData(filmEntity: FilmEntity) {

        detailFilmBinding.tvTitle.text = filmEntity.title
        detailFilmBinding.tvDescription.text = filmEntity.description
        detailFilmBinding.tvReleaseYear.text = filmEntity.releaseYear
        setGlideImage(this@DetailFilmActivity, API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + filmEntity.imgPoster, detailFilmBinding.imgItemPhoto)
        setGlideImage(this@DetailFilmActivity, API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W780 + filmEntity.imgBackground, detailFilmBinding.imgItemPreview)

        detailFilmBinding.imgShare.setOnClickListener{
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,resources.getString(R.string.share_text, detailFilmBinding.tvTitle.text))
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
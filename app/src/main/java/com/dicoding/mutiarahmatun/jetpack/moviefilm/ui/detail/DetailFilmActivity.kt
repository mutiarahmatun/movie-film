package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mutiarahmatun.jetpack.moviefilm.R
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.TYPE_MOVIE
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.TYPE_TV_SHOW
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.setGlideImage
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.ActivityDetailFilmBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.FilmEntity

class DetailFilmActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var filmEntity: FilmEntity
    private lateinit var detailFilmBinding: ActivityDetailFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(detailFilmBinding.root)

        val viewModel = ViewModelProvider(this@DetailFilmActivity, ViewModelProvider.NewInstanceFactory())[DetailFilmViewModel::class.java]

        supportActionBar?.title = "Detail Film"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(EXTRA_DATA)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            id?.let {
                viewModel.setMovieId(it)
            }
            filmEntity = viewModel.getDetailMovieById()
        } else if (type.equals(TYPE_TV_SHOW, ignoreCase = true)) {
            id?.let {
                viewModel.setTvShowId(it)
            }
            filmEntity = viewModel.getDetailTvShowById()
        }

        detailFilmBinding.tvTitle.text = filmEntity.title
        detailFilmBinding.tvDescription.text = filmEntity.description
        detailFilmBinding.tvReleaseYear.text = filmEntity.releaseYear
        detailFilmBinding.tvGenre.text = filmEntity.genre
        setGlideImage(this@DetailFilmActivity, filmEntity.imgPoster, detailFilmBinding.imgItemPhoto)
        setGlideImage(this@DetailFilmActivity, filmEntity.imgBackground, detailFilmBinding.imgItemPreview)

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
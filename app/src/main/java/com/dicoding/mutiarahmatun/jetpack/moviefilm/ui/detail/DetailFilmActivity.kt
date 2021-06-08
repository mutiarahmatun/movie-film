package com.dicoding.mutiarahmatun.jetpack.moviefilm.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mutiarahmatun.jetpack.moviefilm.BuildConfig
import com.dicoding.mutiarahmatun.jetpack.moviefilm.R
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.MovieEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.source.local.entity.TvShowEntity
import com.dicoding.mutiarahmatun.jetpack.moviefilm.databinding.ActivityDetailFilmBinding
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.Constants
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.Constants.TYPE_MOVIE
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.Constants.TYPE_TV_SHOW
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.loadFromUrl
import com.dicoding.mutiarahmatun.jetpack.moviefilm.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
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

        setupViewModel()

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            observeDetailMovie(id)

        } else if (type.equals(TYPE_TV_SHOW, ignoreCase = true)) {
            observeDetailTvShow(id)
        }

    }

    private fun observeDetailMovie(movieId: Int) {
        viewModel.getMovieDetail(movieId).observe(this, Observer {
            displayData(it, null)
        })
    }

    private fun observeDetailTvShow(tvShowId: Int) {
        viewModel.getTvShowDetail(tvShowId).observe(this, Observer {
            it?.let {
                displayData(null, it)
            }
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
                this@DetailFilmActivity,
                factory
        )[DetailFilmViewModel::class.java]
    }

    private fun displayData(movie: MovieEntity?, tvShow: TvShowEntity?) {
        val urlImage = movie?.poster ?: tvShow?.poster
        val urlHighlight = movie?.imgPreview ?: tvShow?.imgPreview
        val statusFavorite = movie?.isFavorite ?: tvShow?.isFavorite

        detailFilmBinding.tvTitle.text = movie?.name ?: tvShow?.name
        detailFilmBinding.tvDescription.text = movie?.desc ?: tvShow?.desc
        detailFilmBinding.tvReleaseYear.text = movie?.releaseYear ?: tvShow?.releaseYear

        detailFilmBinding.imgShare.setOnClickListener{
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,resources.getString(R.string.share_text, detailFilmBinding.tvTitle.text))
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }

        statusFavorite?.let { status ->
            setFavoriteState(status)
        }

        detailFilmBinding.imgItemPhoto.loadFromUrl(BuildConfig.BASE_URL_IMAGE_TMDB + Constants.ENDPOINT_POSTER_SIZE_W185 + urlImage)
        detailFilmBinding.imgItemPreview.loadFromUrl(BuildConfig.BASE_URL_IMAGE_TMDB + Constants.ENDPOINT_POSTER_SIZE_W780 + urlHighlight)

        detailFilmBinding.fabFavorite.setOnClickListener {
            setFavorite(movie, tvShow)
        }

    }

    private fun setFavoriteState(status: Boolean){
        if (status) {
            detailFilmBinding.fabFavorite.setImageResource(R.drawable.ic_favorite_true)
        } else {
            detailFilmBinding.fabFavorite.setImageResource(R.drawable.ic_favorite_false)
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun setFavorite(movie: MovieEntity?, tvShow: TvShowEntity?) {
        if (movie != null) {
            if (movie.isFavorite){
                showSnackBar("${movie.name} Removed from favorite")
            }else {
                showSnackBar("${movie.name} Added to favorite")
            }
            viewModel.setFavoriteMovie(movie)
        } else {
            if (tvShow != null) {
                if (tvShow.isFavorite){
                    showSnackBar("${tvShow.name} Aemoved from favorite")
                }else {
                    showSnackBar("${tvShow.name} Removed from favorite")
                }
                viewModel.setFavoriteTvShow(tvShow)
            }
        }
    }

    private fun setupToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
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
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.TYPE_MOVIE
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.TYPE_TV_SHOW
import com.dicoding.mutiarahmatun.jetpack.moviefilm.utils.ObjectFilmHelper.loadFromUrl
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
    lateinit var viewModelFactory: ViewModelFactory

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
                viewModelFactory
        )[DetailFilmViewModel::class.java]
    }

    private fun displayData(movie: MovieEntity?, tvShow: TvShowEntity?) {
        val urlImage = movie?.imgPoster ?: tvShow?.imgPoster
        val urlHighlight = movie?.imgBackground ?: tvShow?.imgBackground
        val statusFavorite = movie?.isFavorite ?: tvShow?.isFavorite

        detailFilmBinding.tvTitle.text = movie?.title ?: tvShow?.title
        detailFilmBinding.tvDescription.text = movie?.description ?: tvShow?.description
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

        detailFilmBinding.imgItemPhoto.loadFromUrl(
                BuildConfig.BASE_URL_IMAGE_TMDB +
                ObjectFilmHelper.ENDPOINT_POSTER_SIZE_W185 +
                        urlImage)

        detailFilmBinding.imgItemPreview.loadFromUrl(
                BuildConfig.BASE_URL_IMAGE_TMDB +
                        ObjectFilmHelper.ENDPOINT_POSTER_SIZE_W780 +
                        urlHighlight)

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
                showSnackBar("${movie.title} Removed from favorite movie")
            }else {
                showSnackBar("${movie.title} Added to favorite movie")
            }
            viewModel.setFavoriteMovie(movie)

        } else {
            if (tvShow != null) {
                if (tvShow.isFavorite){
                    showSnackBar("${tvShow.title} Removed from favorite TV show")
                }else {
                    showSnackBar("${tvShow.title} Added from favorite TV show")
                }
                viewModel.setFavoriteTvShow(tvShow)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
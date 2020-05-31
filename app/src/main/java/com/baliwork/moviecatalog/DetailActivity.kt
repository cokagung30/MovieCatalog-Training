package com.baliwork.moviecatalog

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.baliwork.moviecatalog.model.Movie
import com.baliwork.moviecatalog.model.TvShow
import com.baliwork.moviecatalog.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID = "movie_id"
        const val TYPE = "type"
    }

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailViewModel = ViewModelProviders.of(this)
            .get(DetailViewModel::class.java)
        val movieId = intent.getIntExtra(MOVIE_ID, 0)
        val type = intent.getStringExtra(TYPE)

        setSupportActionBar(toolbar_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (type == MovieFragment.TYPE_MOVIE) {
            detailViewModel.setDetailMovie(movieId)
            detailViewModel.getMovie()
                .observe(this, showDetailMovie)
        } else if (type == TvShowFragment.TYPE_TV_SHOW) {
            detailViewModel.setDetailTvShow(movieId)
            detailViewModel.getDetailTvShow().observe(this, showTvShowDetail)
        }

    }

    private fun addFavorite(
        type: String?, movie: Movie?,
        tvShow: TvShow?
    ) {
        fab_favorite.setOnClickListener {
            if (type == MovieFragment.TYPE_MOVIE) {
                val check = detailViewModel
                    .getDetailMovieFavorite(movie!!.id)
                if (check != null) {
                    Toast.makeText(
                        this,
                        "${movie.title} dihapus dari favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                    setFabColorNotFavorite()
                    detailViewModel.removeFavoriteMovie(movie)

                } else {
                    Toast.makeText(
                        this,
                        "${movie.title} ditambahkan ke favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                    setFabColorFavorite()
                    detailViewModel.favoriteMovie(movie)
                }

            }
            else if (type == TvShowFragment.TYPE_TV_SHOW) {
                val check = detailViewModel
                    .getDetailTvShowFavorite(tvShow!!.id)

                if (check != null) {
                    Toast.makeText(
                        this,
                        "${tvShow.original_name} dihapus dari favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                    setFabColorNotFavorite()
                    detailViewModel.favoriteTvShow(tvShow)
                } else {
                    Toast.makeText(
                        this,
                        "${tvShow.original_name} ditambahkan ke favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                    setFabColorFavorite()
                    detailViewModel.removeTvShow(tvShow)
                }

            }
        }
    }

    private val showDetailMovie = Observer<Movie> {
        val backdrop = "${BuildConfig.URL_IMG}w500${it.backdrop_path}"
        val poster = "${BuildConfig.URL_IMG}w185${it.poster_path}"

        tv_rating_movie_detail.text = "${it.vote_average}"
        rate_movie_detail.rating = it.vote_average.toFloat() / 2
        tv_title_movie_detail.text = it.title
        tv_release_date_detail.text = "Release: ${it.release_date}"
        Glide.with(this).load(backdrop).into(iv_backdrop)
        Glide.with(this).load(poster).into(iv_poster_detail)
        tv_overview_detail.text = it.overview

        setUpCollapsingToolbar(it.title)
        addFavorite(MovieFragment.TYPE_MOVIE, it, null)
        val check = detailViewModel.getDetailMovieFavorite(it.id)
        if (check != null) {
            setFabColorFavorite()
        } else {
            setFabColorNotFavorite()
        }
    }

    private val showTvShowDetail = Observer<TvShow> {
        val backdrop = "${BuildConfig.URL_IMG}w500${it.backdrop_path}"
        val poster = "${BuildConfig.URL_IMG}w185${it.poster_path}"

        tv_rating_movie_detail.text = "${it.vote_average}"
        rate_movie_detail.rating = it.vote_average.toFloat() / 2
        tv_title_movie_detail.text = it.original_name
        tv_release_date_detail.text = "Release: ${it.first_air_date}"
        Glide.with(this).load(backdrop).into(iv_backdrop)
        Glide.with(this).load(poster).into(iv_poster_detail)
        tv_overview_detail.text = it.overview

        setUpCollapsingToolbar(it.original_name)
        addFavorite(TvShowFragment.TYPE_TV_SHOW, null, it)
        val check = detailViewModel.getDetailTvShowFavorite(it.id)
        if (check != null) {
            setFabColorFavorite()
        } else {
            setFabColorNotFavorite()
        }
    }

    private fun setUpCollapsingToolbar(title: String) {
        collapsing_toolbar.title = title
        collapsing_toolbar
            .setExpandedTitleColor(
                ContextCompat
                    .getColor(applicationContext, android.R.color.white)
            )
    }

    private fun setFabColorNotFavorite() {
        fab_favorite.setColorFilter(
            ContextCompat.getColor(applicationContext,
                android.R.color.white)
        )
    }

    private fun setFabColorFavorite() {
        fab_favorite.setColorFilter(
            ContextCompat.getColor(applicationContext,
                android.R.color.holo_orange_light)
        )
    }
}

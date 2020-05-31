package com.baliwork.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baliwork.moviecatalog.BuildConfig
import com.baliwork.moviecatalog.model.Movie
import com.baliwork.moviecatalog.model.TvShow
import com.baliwork.moviecatalog.server.API
import com.baliwork.moviecatalog.server.Common
import com.baliwork.moviecatalog.sqlite.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private lateinit var apiRest: API
    private val movie = MutableLiveData<Movie>()
    private val tvShow = MutableLiveData<TvShow>()
    private var key = BuildConfig.API_KEY

    fun setDetailMovie(movieId: Int) {
        apiRest = Common.apiRest
        apiRest.fetchDetailMovie(movieId, key)
            .enqueue(object : Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    movie.postValue(null)
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.body() != null) {
                        movie.value = response.body()
                    }
                }
            })
    }

    fun getMovie(): LiveData<Movie> = movie

    fun setDetailTvShow(movieId: Int) {
        apiRest = Common.apiRest
        apiRest.fetchDetailTvShow(movieId, key)
            .enqueue(object : Callback<TvShow> {
                override fun onFailure(call: Call<TvShow>, t: Throwable) {
                    tvShow.value = null
                }

                override fun onResponse(
                    call: Call<TvShow>,
                    response: Response<TvShow>
                ) {
                    if (response.body() != null) {
                        tvShow.value = response.body()
                    }
                }
            })
    }

    fun getDetailTvShow(): LiveData<TvShow> = tvShow

    fun getDetailMovieFavorite(id: Int): Movie {
        return AppDatabase.getInstance()
            .movieDao().getFavoriteMovie(id)
    }

    fun getDetailTvShowFavorite(id: Int): TvShow {
        return AppDatabase.getInstance()
            .tvShowDao().getFavoriteTvShow(id)
    }

    fun favoriteMovie(movie: Movie) {
        AppDatabase.getInstance().movieDao().addFavoriteMovie(movie)
    }

    fun removeFavoriteMovie(movie: Movie) {
        AppDatabase.getInstance().movieDao().removeFavoriteMovie(movie)
    }

    fun favoriteTvShow(tvShow: TvShow) {
        AppDatabase.getInstance().tvShowDao().addFavoriteTvShow(tvShow)
    }

    fun removeTvShow(tvShow: TvShow) {
        AppDatabase.getInstance().tvShowDao().removeFavoriteTvShow(tvShow)
    }
}
package com.baliwork.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baliwork.moviecatalog.BuildConfig
import com.baliwork.moviecatalog.model.Movie
import com.baliwork.moviecatalog.server.Common
import com.baliwork.moviecatalog.server.Responses
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movies = MutableLiveData<List<Movie>>()
    private var key  = BuildConfig.API_KEY

    fun setMovie() {
        val apiRest = Common.apiRest
        apiRest.fetchMovie(key).enqueue(object : Callback<Responses> {
            override fun onFailure(call: Call<Responses>, t: Throwable) {
                movies.value = null
            }

            override fun onResponse(call: Call<Responses>,
                                    response: Response<Responses>) {
                    if (response.body() != null) {
                        movies.value = response.body()!!.results
                    }
            }

        })
    }

    fun getMovies() : LiveData<List<Movie>> = movies
}
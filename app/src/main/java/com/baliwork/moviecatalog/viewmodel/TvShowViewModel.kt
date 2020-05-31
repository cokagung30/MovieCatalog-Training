package com.baliwork.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baliwork.moviecatalog.BuildConfig
import com.baliwork.moviecatalog.model.TvShow
import com.baliwork.moviecatalog.server.API
import com.baliwork.moviecatalog.server.Common
import com.baliwork.moviecatalog.server.response.TvShowResponse
import com.baliwork.moviecatalog.sqlite.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel : ViewModel() {
    private lateinit var apiRest: API
    private val tvShow = MutableLiveData<List<TvShow>>()
    private val key = BuildConfig.API_KEY

    fun setTvShow() {
        apiRest = Common.apiRest
        apiRest.fetchTvShow(key).enqueue(object : Callback<TvShowResponse> {
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                tvShow.postValue(null)
            }

            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.body() != null) {
                    tvShow.postValue(response.body()!!.result)
                }
            }
        })
    }

    fun getTvShow(): LiveData<List<TvShow>> = tvShow

    fun getAllTvShowFavorite(): LiveData<List<TvShow>> {
        return AppDatabase.getInstance().tvShowDao()
            .getAllFavoriteTvShow()
    }
}
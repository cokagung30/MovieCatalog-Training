package com.baliwork.moviecatalog.server

import com.baliwork.moviecatalog.server.response.Responses
import com.baliwork.moviecatalog.server.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("movie/popular")
    fun fetchMovie(@Query("api_key") apiKey : String) : Call<Responses>

    @GET("tv/popular")
    fun fetchTvShow(@Query("api_key") apiKey: String) : Call<TvShowResponse>
}
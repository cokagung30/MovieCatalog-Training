package com.baliwork.moviecatalog.server

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("movie/popular")
    fun fetchMovie(@Query("api_key") apiKey : String) : Call<Responses>
}
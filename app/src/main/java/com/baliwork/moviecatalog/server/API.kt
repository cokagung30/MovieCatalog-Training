package com.baliwork.moviecatalog.server

import com.baliwork.moviecatalog.model.Movie
import com.baliwork.moviecatalog.model.TvShow
import com.baliwork.moviecatalog.server.response.Responses
import com.baliwork.moviecatalog.server.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("movie/popular")
    fun fetchMovie(@Query("api_key") apiKey : String) : Call<Responses>

    @GET("tv/popular")
    fun fetchTvShow(@Query("api_key") apiKey: String) : Call<TvShowResponse>

    @GET("movie/{movie_id}")
    fun fetchDetailMovie(@Path("movie_id") movieId: Int,
                        @Query("api_key")key: String)
            : Call<Movie>

    @GET("tv/{movie_id}")
    fun fetchDetailTvShow(@Path("movie_id") movieId: Int,
                        @Query("api_key")key: String)
            : Call<TvShow>
}
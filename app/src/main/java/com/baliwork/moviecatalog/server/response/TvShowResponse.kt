package com.baliwork.moviecatalog.server.response

import com.baliwork.moviecatalog.model.TvShow
import com.google.gson.annotations.SerializedName

class TvShowResponse (
    @SerializedName("results")
    val result: List<TvShow>
)
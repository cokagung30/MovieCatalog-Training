package com.baliwork.moviecatalog.interfaces

import com.baliwork.moviecatalog.model.TvShow

interface TvShowClickListener {
    fun onClick(position: Int, movies: List<TvShow>)
}
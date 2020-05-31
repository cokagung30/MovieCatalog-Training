package com.baliwork.moviecatalog.interfaces

import com.baliwork.moviecatalog.model.Movie

interface MovieClickListener {
    fun onClick(position: Int, movies: List<Movie>)
}
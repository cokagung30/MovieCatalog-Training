package com.baliwork.moviecatalog.sqlite.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.baliwork.moviecatalog.model.Movie

@Dao
interface MovieDao  {
    @Insert
    fun addFavoriteMovie(movie: Movie)

    @Delete
    fun removeFavoriteMovie(movie: Movie)

    @Query("Select * from movie where id=:id")
    fun getFavoriteMovie(id: Int) : Movie

    @Query("Select * from movie")
    fun getAllFavoriteMovie() : LiveData<List<Movie>>
}
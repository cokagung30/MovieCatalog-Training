package com.baliwork.moviecatalog.sqlite.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.baliwork.moviecatalog.model.TvShow

@Dao
interface TvShowDao {
    @Insert
    fun addFavoriteTvShow(tvShow: TvShow)

    @Delete
    fun removeFavoriteTvShow(tvShow: TvShow)

    @Query("Select * from tv_show where id=:id")
    fun getFavoriteTvShow(id: Int): TvShow

    @Query("Select * from tv_show")
    fun getAllFavoriteTvShow(): LiveData<List<TvShow>>
}
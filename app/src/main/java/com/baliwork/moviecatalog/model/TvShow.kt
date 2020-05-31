package com.baliwork.moviecatalog.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show")
class TvShow (
    @PrimaryKey
    val id: Int,
    val original_name: String,
    val vote_average: Double,
    val  poster_path: String,
    val first_air_date: String,
    val overview: String,
    val backdrop_path: String?
)
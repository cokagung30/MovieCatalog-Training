package com.baliwork.moviecatalog.sqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baliwork.moviecatalog.model.Movie
import com.baliwork.moviecatalog.model.TvShow
import com.baliwork.moviecatalog.sqlite.dao.MovieDao
import com.baliwork.moviecatalog.sqlite.dao.TvShowDao

@Database(
    entities = [Movie::class, TvShow::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao
    abstract fun tvShowDao(): TvShowDao

    companion object {
        private lateinit var instance : AppDatabase
        fun getInstance(): AppDatabase = instance
        fun createDatabase(context: Context) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "catalog_movie"
            ).allowMainThreadQueries().build()
        }
    }
}
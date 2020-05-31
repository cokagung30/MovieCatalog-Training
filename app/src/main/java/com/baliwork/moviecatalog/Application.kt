package com.baliwork.moviecatalog

import android.app.Application
import com.baliwork.moviecatalog.sqlite.AppDatabase

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.createDatabase(this)
    }
}
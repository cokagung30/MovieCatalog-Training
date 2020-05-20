package com.baliwork.moviecatalog.server

object Common {
    private var url = "https://api.themoviedb.org/3/"
    val apiRest: API get() = Client.getClient(url).create(API::class.java)
}
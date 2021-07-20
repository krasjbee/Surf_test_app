package com.example.surf_test_app

import retrofit2.http.GET

interface TMDbAPI {
    @GET("discover/movie")
    //Todo add query parameters
    suspend fun getMovies()
    //todo add query parameters
    @GET("search/movie")
    suspend fun searchMovies()
}
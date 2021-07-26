package com.example.surf_test_app

import com.example.surf_test_app.model.DiscoverResponse
import com.example.surf_test_app.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbAPI {
    @GET("discover/movie")
    //Todo add query parameters
    suspend fun getMovies(): DiscoverResponse

    //todo add query parameters
    @GET("search/movie")
    suspend fun searchMovies(@Query("query") searchQuery: String): SearchResponse
}
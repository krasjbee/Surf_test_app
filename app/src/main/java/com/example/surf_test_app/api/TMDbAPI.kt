package com.example.surf_test_app.api

import com.example.surf_test_app.model.DiscoverResponse
import com.example.surf_test_app.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbAPI {

    @GET("discover/movie")
    suspend fun getMovies(): Response<DiscoverResponse>

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") searchQuery: String): Response<SearchResponse>
}
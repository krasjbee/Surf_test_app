package com.example.surf_test_app.repository

import com.example.surf_test_app.model.DiscoverResponse
import com.example.surf_test_app.model.SearchResponse
import retrofit2.Response

interface FilmsRepository {
    suspend fun getFilms(): Response<DiscoverResponse>

    suspend fun searchFilms(searchQuery: String): Response<SearchResponse>
}
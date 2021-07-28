package com.example.surf_test_app.repository

import com.example.surf_test_app.model.DiscoverResponse
import com.example.surf_test_app.model.SearchResponse

interface FilmsRepository {
    suspend fun getFilms(): DiscoverResponse

    suspend fun searchFilms(searchQuery: String): SearchResponse
}
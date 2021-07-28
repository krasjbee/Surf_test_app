package com.example.surf_test_app.repository

import com.example.surf_test_app.TMDbAPI
import com.example.surf_test_app.model.DiscoverResponse
import com.example.surf_test_app.model.SearchResponse
import retrofit2.Response
import javax.inject.Inject

class FilmRepositoryImp @Inject constructor(private val api: TMDbAPI) : FilmsRepository {

    override suspend fun getFilms(): Response<DiscoverResponse> = api.getMovies()

    override suspend fun searchFilms(searchQuery: String): Response<SearchResponse> =
        api.searchMovies(searchQuery)
}
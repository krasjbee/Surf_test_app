package com.example.surf_test_app.domain

import com.example.surf_test_app.model.DiscoverResponse
import com.example.surf_test_app.model.SearchResponse
import com.example.surf_test_app.repository.FilmsRepository
import com.example.surf_test_app.util.Resource
import javax.inject.Inject

class FilmsInteractor @Inject constructor(val repository: FilmsRepository) {

    suspend operator fun invoke(): Resource<DiscoverResponse> {
        try {
            val response = repository.getFilms()
            return if (response.code() == 200 && response.body() != null) {
                Resource.Success<DiscoverResponse>(response.body()!!)
            } else {
                Resource.RequestError<DiscoverResponse>(response.errorBody().toString())
            }
        } catch (e: Exception) {
            return Resource.UnexpectedError<DiscoverResponse>(e.message)
        }
    }

    suspend operator fun invoke(query: String): Resource<SearchResponse> {
        try {
            val response = repository.searchFilms(query)
            return if (response.code() == 200 && response.body() != null) {
                Resource.Success<SearchResponse>(response.body()!!)
            } else {
                Resource.RequestError<SearchResponse>(response.errorBody().toString())
            }

        } catch (e: Exception) {
            return Resource.UnexpectedError<SearchResponse>(e.message ?: "Unexpected error")
        }
    }
}
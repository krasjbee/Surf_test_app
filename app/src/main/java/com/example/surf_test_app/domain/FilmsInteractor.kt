package com.example.surf_test_app.domain

import com.example.surf_test_app.model.DiscoverResponse
import com.example.surf_test_app.model.SearchResponse
import com.example.surf_test_app.repository.FilmsRepository
import com.example.surf_test_app.util.Resource
import javax.inject.Inject

class FilmsInteractor @Inject constructor(val repository: FilmsRepository) {

    suspend operator fun invoke(): Resource<DiscoverResponse> {
        return try {
            val response = repository.getFilms()
            if (response.code() == 200 && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.RequestError(response.errorBody().toString())
            }
        } catch (e: Exception) {
            Resource.UnexpectedError(e.message)
        }
    }

    suspend operator fun invoke(query: String): Resource<SearchResponse> {
        return try {
            val response = repository.searchFilms(query)
            if (response.code() == 200 && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.RequestError(response.errorBody().toString())
            }

        } catch (e: Exception) {
            Resource.UnexpectedError(e.message ?: "Unexpected error")
        }
    }
}
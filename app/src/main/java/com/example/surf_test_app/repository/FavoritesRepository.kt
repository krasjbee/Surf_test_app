package com.example.surf_test_app.repository

import com.example.surf_test_app.model.FilmResult

interface FavoritesRepository {

    suspend fun getFavorites(): List<FilmResult>

    suspend fun deleteFavourite(film: FilmResult)

    suspend fun addFavourite(film: FilmResult)

    fun getSharedFavourites(): Set<String>

    fun deleteSharedFavourite(filmId: String)

    fun addSharedFavourite(filmId: String)

}
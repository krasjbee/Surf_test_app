package com.example.surf_test_app.repository

interface FavoritesRepository {

    fun getSharedFavourites(): Set<String>

    fun deleteSharedFavourite(filmId: String)

    fun addSharedFavourite(filmId: String)

}
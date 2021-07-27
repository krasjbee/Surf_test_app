package com.example.surf_test_app.repository

interface FavoritesRepository {
    suspend fun getFavorites(): List<String>
}
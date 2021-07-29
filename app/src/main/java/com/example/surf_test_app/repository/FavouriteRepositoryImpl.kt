package com.example.surf_test_app.repository

import com.example.surf_test_app.db.FavouriteDao
import com.example.surf_test_app.model.FilmResult
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(private val dao: FavouriteDao) :
    FavoritesRepository {

    override suspend fun getFavorites(): List<FilmResult> = dao.getAllFavourites()

    override suspend fun deleteFavourite(film: FilmResult) = dao.delete(film)

    override suspend fun addFavourite(film: FilmResult) = dao.insertFilm(film)

    override fun getSharedFavourites(): Set<String> {
        return emptySet()
    }

    override fun deleteSharedFavourite(filmId: String) {

    }

    override fun addSharedFavourite(filmId: String) {

    }
}
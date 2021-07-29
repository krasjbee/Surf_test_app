package com.example.surf_test_app.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.surf_test_app.model.FilmResult
import javax.inject.Inject

class FavouriteRepositorySharedImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    FavoritesRepository {
    //    init {
//        sharedPreferences.edit {
//            putStringSet("favourites", emptySet())
//            apply()
//        }
//    }
    override suspend fun getFavorites(): List<FilmResult> {
        return emptyList()
    }

    override suspend fun deleteFavourite(film: FilmResult) {

    }

    override suspend fun addFavourite(film: FilmResult) {

    }

    override fun getSharedFavourites(): Set<String> {
        return sharedPreferences.getStringSet("favourites", emptySet())?.toSet() ?: emptySet()
    }

    override fun deleteSharedFavourite(filmId: String) {
        val idList = sharedPreferences.getStringSet("favourites", emptySet())?.toMutableSet()
        idList?.remove(filmId)
        sharedPreferences.edit {
            putStringSet("favourites", idList)
            apply()
        }
    }

    override fun addSharedFavourite(filmId: String) {
        val idList = sharedPreferences.getStringSet("favourites", emptySet())?.toMutableSet()
        idList?.add(filmId)
        sharedPreferences.edit {
            putStringSet("favourites", idList)
            apply()
        }
    }
}
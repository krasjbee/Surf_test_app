package com.example.surf_test_app.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class FavouriteRepositorySharedImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    FavoritesRepository {


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
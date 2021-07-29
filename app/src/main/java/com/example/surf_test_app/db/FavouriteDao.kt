package com.example.surf_test_app.db

import androidx.room.*
import com.example.surf_test_app.model.FilmResult

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(filmResult: FilmResult)

    @Delete
    suspend fun delete(filmResult: FilmResult)

    @Query("SELECT * FROM favourites")
    suspend fun getAllFavourites(): List<FilmResult>

}
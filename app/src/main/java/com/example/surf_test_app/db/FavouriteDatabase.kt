package com.example.surf_test_app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.surf_test_app.model.FilmResult

@Database(entities = [FilmResult::class], version = 1)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract fun favouritesDao(): FavouriteDao

}
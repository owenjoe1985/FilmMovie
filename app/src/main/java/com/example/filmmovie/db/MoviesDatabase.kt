package com.example.filmmovie.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase(){
    abstract fun movieDao(): MoviesDao
}
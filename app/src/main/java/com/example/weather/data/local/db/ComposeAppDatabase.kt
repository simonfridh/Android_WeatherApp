package com.example.weather.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class ComposeAppDatabase : RoomDatabase(){
    abstract fun weatherDao(): WeatherDao
}
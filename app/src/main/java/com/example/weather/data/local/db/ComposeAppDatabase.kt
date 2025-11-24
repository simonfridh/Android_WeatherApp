package com.example.weather.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.data.local.dao.CurrentWeatherDao
import com.example.weather.data.local.entity.CurrentWeatherEntity

@Database(
    entities = [CurrentWeatherEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun currentWeatherDao(): CurrentWeatherDao
}
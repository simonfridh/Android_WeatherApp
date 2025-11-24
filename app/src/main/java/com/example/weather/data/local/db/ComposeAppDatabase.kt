package com.example.weather.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.data.local.dao.ForecastDao
import com.example.weather.data.local.entity.ForecastEntity

@Database(
    entities = [ForecastEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao
}
package com.example.weather.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather.data.local.converter.ForecastConverter
import com.example.weather.data.local.dao.IForecastDao
import com.example.weather.data.local.entity.ForecastEntity

@Database(
    entities = [ForecastEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ForecastConverter::class)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun forecastDao(): IForecastDao
}
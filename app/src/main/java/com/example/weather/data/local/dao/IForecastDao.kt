package com.example.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.local.entity.ForecastEntity

@Dao
interface IForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (forecast: ForecastEntity)

    @Query("""
        SELECT * FROM forecast 
        WHERE longitude = :lon AND latitude = :lat
        LIMIT 1
    """)
    suspend fun getLatestForecast(lon: Float, lat: Float): ForecastEntity?
}
package com.example.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.local.entity.CurrentWeatherEntity

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (current: CurrentWeatherEntity)

    @Query("SELECT * FROM `current` WHERE longitude = :lon AND latitude = :lat")
    suspend fun getCurrent(lon: Double, lat: Double): CurrentWeatherEntity?
}
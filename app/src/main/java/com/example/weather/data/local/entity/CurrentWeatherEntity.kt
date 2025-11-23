package com.example.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current")
data class CurrentWeatherEntity(
    @PrimaryKey
    val locationKey: String,
    val time: Long,
    val weatherIcon: Int,
    val temperature: Float
)

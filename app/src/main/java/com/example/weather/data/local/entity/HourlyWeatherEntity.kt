package com.example.weather.data.local.entity

import androidx.room.Entity
import java.time.LocalDateTime

@Entity(
    tableName = "hourly",
    primaryKeys = ["time", "longitude", "latitude"])

data class HourlyWeatherEntity(
    val time: LocalDateTime,
    val longitude: Double,
    val latitude: Double,
    val weatherIcon: Int,
    val temperature: Float,
)

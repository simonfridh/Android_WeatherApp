package com.example.weather.data.local.entity

import androidx.room.Entity
import java.time.LocalDateTime

@Entity(
    tableName = "forecast",
    primaryKeys = ["longitude", "latitude", "timestamp"]
)
data class ForecastEntity(
    val longitude: Float,
    val latitude: Float,
    val timestamp: LocalDateTime,

    val forecastJson: String,
)
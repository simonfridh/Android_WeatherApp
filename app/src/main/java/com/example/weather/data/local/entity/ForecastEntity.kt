package com.example.weather.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "forecast",
    primaryKeys = ["longitude", "latitude", "timestamp"]
)
data class ForecastEntity(
    val longitude: Double,
    val latitude: Double,
    val timestamp: Long,

    val forecastJson: String,
)
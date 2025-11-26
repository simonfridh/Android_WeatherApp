package com.example.weather.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "forecast",
    primaryKeys = ["longitude", "latitude"]
)
data class ForecastEntity(
    val longitude: Float,
    val latitude: Float,

    val forecastJson: String,
)

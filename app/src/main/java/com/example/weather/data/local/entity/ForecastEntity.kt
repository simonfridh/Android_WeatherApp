package com.example.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "forecast"
)

data class ForecastEntity(
    @PrimaryKey
    val name: String,

    val forecastJson: String,
)

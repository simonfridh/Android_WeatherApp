package com.example.weather.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "current",
    primaryKeys = ["longitude", "latitude", "date"] //todo Byt ut mot något bättre?
)
data class DailyWeatherEntity(
    val longitude: Double,
    val latitude: Double,
    val date: Long,

    val weatherIcon: Int,
    val maxTemp: Float,
    val minTemp: Float
)

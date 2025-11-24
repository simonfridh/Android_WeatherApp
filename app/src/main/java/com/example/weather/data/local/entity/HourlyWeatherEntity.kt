package com.example.weather.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "current",
    primaryKeys = ["longitude", "latitude", "time"] //todo Byt ut mot något bättre?
)
data class HourlyWeatherEntity(
    val longitude: Double,
    val latitude: Double,
    val time: Long,

    val weatherIcon: Int,
    val temperature: Float,
)

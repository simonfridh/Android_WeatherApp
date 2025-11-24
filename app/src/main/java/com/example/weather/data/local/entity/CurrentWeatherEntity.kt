package com.example.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "current",
    primaryKeys = ["longitude", "latitude", "time"] //todo Byt ut mot något bättre?
)
data class CurrentWeatherEntity(
    val longitude: Double,
    val latitude: Double,

    val time: Long,
    val weatherIcon: Int,
    val temperature: Float
)

package com.example.weather.data.remote.dto

data class Current(
    val time: String,
    val weather_code: Int,
    val temperature_2m: Double
)
package com.example.weather.data.remote.weatherapi.dto

data class Current(
    val time: String,
    val weather_code: Int,
    val temperature_2m: Double
)
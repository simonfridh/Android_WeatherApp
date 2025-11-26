package com.example.weather.data.remote.dto

data class ForecastResponseDto(
    val longitude: Double?,
    val latitude: Double?,

    val current: Current?,
    val hourly: Hourly?,
    val daily: Daily?,
)
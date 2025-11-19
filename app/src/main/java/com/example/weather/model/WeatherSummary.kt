package com.example.weather.model

data class WeatherSummary(
    val date: String,
    val minTemp: Double,
    val maxTemp: Double,
    val precipitationRisk: Int,
    val windSpeed: Double,
    val humidity: Int,
    val weatherSymbol: Int
)


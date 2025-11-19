package com.example.weather.model

data class Weather(
    val date: String,
    val temperature: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val precipitationRisk: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val gust: Double,
    val humidity: Int,
    val weatherSymbol: Int,
    val description: String = "",
    val icon: Int = 0
)
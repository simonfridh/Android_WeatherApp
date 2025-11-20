package com.example.weather.model

data class Forecast (
    val longitude: Float,
    val latitude: Float,

    val currentWeather: Weather,
    val hourlyWeather: List<Weather>,
    val dailyWeather: List<Weather>
)



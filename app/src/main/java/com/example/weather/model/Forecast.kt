package com.example.weather.model

data class Forecast (
    val longitude: Float,
    val latitude: Float,

    val currentWeather: Weather.Current,
    val hourlyWeather: List<Weather.Hourly>,
    val dailyWeather: List<Weather.Daily>
)



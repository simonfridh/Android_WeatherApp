package com.example.weather.data.remote.weatherapi.dto

data class Hourly(
    val time: List<String>,
    val weather_code: List<Int>,
    val temperature_2m: List<Double>
)
package com.example.weather.data.remote.dto

data class ForecastResponseDto(
    val approvedTime: String,
    val referenceTime: String,
    val geometry: Geometry,
    val timeSeries: List<TimeSeries>
)

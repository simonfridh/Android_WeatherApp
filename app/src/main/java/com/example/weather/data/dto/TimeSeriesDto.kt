package com.example.weather.data.dto

data class TimeSeriesDto(
    val time: String,
    val parameters: List<ParameterDto>
)

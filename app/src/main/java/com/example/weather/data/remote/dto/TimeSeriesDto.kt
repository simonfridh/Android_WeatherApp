package com.example.weather.data.remote.dto

data class TimeSeriesDto(
    val time: String,
    val parameters: List<ParameterDto>
)

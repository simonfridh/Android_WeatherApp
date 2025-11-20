package com.example.weather.data.remote.dto

data class TimeSeries(
    val validTime: String,
    val parameters: List<Parameter>
)
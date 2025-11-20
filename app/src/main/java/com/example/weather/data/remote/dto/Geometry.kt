package com.example.weather.data.remote.dto

data class Geometry(
    val type: String,
    val coordinates: List<List<Double>>
)
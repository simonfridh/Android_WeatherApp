package com.example.weather.data.dto

data class ParameterDto(
    val name: String,
    val levelType: String?,
    val level: Int?,
    val unit: String?,
    val values: List<Double>
)

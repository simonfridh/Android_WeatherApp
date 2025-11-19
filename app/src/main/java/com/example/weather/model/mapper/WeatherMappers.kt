package com.example.weather.model.mapper

import com.example.weather.data.remote.dto.ParameterDto
import com.example.weather.data.remote.dto.TimeSeriesDto
import com.example.weather.model.Weather
import com.example.weather.model.WeatherSummary
import kotlin.String

fun List<ParameterDto>.valueOfDouble(name: String): Double? =
    firstOrNull { it.name == name }?.values?.firstOrNull()

// Helper: gets first value as Int
fun List<ParameterDto>.valueOfInt(name: String): Int? =
    firstOrNull { it.name == name }?.values?.firstOrNull()?.toInt()


fun TimeSeriesDto.toWeather(): Weather {
    val p = parameters

    return Weather(
        date = time ?: "unknown",
        temperature = p.valueOfDouble("t") ?: 0.0,
        minTemp = p.valueOfDouble("tmin") ?: 0.0,
        maxTemp = p.valueOfDouble("tmax") ?: 0.0,
        windSpeed = p.valueOfDouble("ws") ?: 0.0,
        windDirection = p.valueOfDouble("wd")?.toInt() ?: 0,
        gust = p.valueOfDouble("gust") ?: 0.0,
        humidity = p.valueOfInt("r")?: 0,
        precipitationRisk = p.valueOfInt("pcat")?: 0,
        weatherSymbol = p.valueOfInt("Wsymb2")?: 0,

        //description = TODO(),
        //icon = TODO()
    )
}

fun Weather.toSummary(): WeatherSummary {
    return WeatherSummary(
        date = date,
        minTemp = minTemp,
        maxTemp = maxTemp,
        precipitationRisk = precipitationRisk,
        windSpeed = windSpeed,
        humidity = humidity,
        weatherSymbol = weatherSymbol
    )
}
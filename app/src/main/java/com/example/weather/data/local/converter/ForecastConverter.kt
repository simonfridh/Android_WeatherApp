package com.example.weather.data.local.converter

import com.example.weather.domain.models.Forecast
import com.google.gson.Gson

object ForecastConverter {
    fun fromForecast(forecast: Forecast): String {
        return Gson().toJson(forecast)
    }

    fun toForecast(json: String): Forecast {
        return Gson().fromJson(json, Forecast::class.java)
    }
}
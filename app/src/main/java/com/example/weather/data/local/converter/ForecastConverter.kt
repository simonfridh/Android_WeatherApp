package com.example.weather.data.local.converter

import androidx.room.TypeConverter
import com.example.weather.domain.models.Forecast
import com.google.gson.Gson

object ForecastConverter {
    @TypeConverter
    fun fromForecast(forecast: Forecast): String {
        return Gson().toJson(forecast)
    }
    @TypeConverter
    fun toForecast(json: String): Forecast {
        return Gson().fromJson(json, Forecast::class.java)
    }
}
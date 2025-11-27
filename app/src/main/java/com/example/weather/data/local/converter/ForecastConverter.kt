package com.example.weather.data.local.converter

import androidx.room.TypeConverter
import com.example.weather.domain.models.forecast.Forecast
import com.google.gson.Gson

object ForecastConverter {

    private val gson: Gson = GsonProvider().gson
    @TypeConverter
    fun fromForecast(forecast: Forecast): String {
        return gson.toJson(forecast)
    }
    @TypeConverter
    fun toForecast(json: String): Forecast {
        return gson.fromJson(json, Forecast::class.java)
    }
}
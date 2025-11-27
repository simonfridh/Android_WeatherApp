package com.example.weather.domain.repository

import com.example.weather.domain.models.forecast.Forecast
import com.example.weather.domain.models.placename.Placename

interface IWeatherRepository {
    suspend fun getForecastRemote(lon: Float, lat: Float): Result<Forecast>
    suspend fun getForecastLocal(lon: Float, lat: Float): Result<Forecast>
    suspend fun saveForecastToLocal(longitude:Float, latitude:Float, forecast: Forecast)
    suspend fun getPlacenameRemote(name: String): Result<Placename>
}
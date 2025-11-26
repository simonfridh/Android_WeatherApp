package com.example.weather.domain.repository

import com.example.weather.domain.models.Forecast

interface IWeatherRepository {
    suspend fun getForecastRemote(lon: Float, lat: Float): Result<Forecast>
    suspend fun getForecastLocal(lon: Float, lat: Float): Result<Forecast>

    suspend fun saveForecastToLocal(forecast: Forecast)
}
package com.example.weather.domain.repository

import com.example.weather.domain.models.Forecast

interface IWeatherRepository {
    suspend fun getForecastRemote(lon: Float, lat: Float): Forecast
    suspend fun getForecastLocal(lon: Float, lat: Float): Forecast?
}
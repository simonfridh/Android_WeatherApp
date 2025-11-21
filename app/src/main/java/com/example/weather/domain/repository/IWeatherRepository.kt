package com.example.weather.domain.repository

import com.example.weather.domain.models.Forecast

interface IWeatherRepository {
    suspend fun getForecast(lon: Float, lat: Float): Forecast
}
package com.example.weather.data.remote.weatherapi

import com.example.weather.data.remote.weatherapi.dto.ForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApi {
    @GET("v1/forecast?daily=weather_code,temperature_2m_max,temperature_2m_min&hourly=temperature_2m,weather_code&current=temperature_2m,weather_code&forecast_hours=24")
    suspend fun getForecast(
        @Query("longitude") longitude: Float,
        @Query("latitude") latitude: Float,
    ): ForecastResponseDto
}
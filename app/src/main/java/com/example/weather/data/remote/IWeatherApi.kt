package com.example.weather.data.remote

import com.example.weather.data.remote.dto.ForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.Query


interface IWeatherApi {
    @GET("weather/forecast")
    suspend fun getForecast(
        @Query("lonLat") lonLat: String
    ): ForecastResponseDto
}
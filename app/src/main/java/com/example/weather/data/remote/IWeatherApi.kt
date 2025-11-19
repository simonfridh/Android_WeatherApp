package com.example.weather.data.remote

import com.example.weather.data.dto.ForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface IWeatherApi {
    @GET("weather/forecast")
    suspend fun getWeather(
        @QueryMap queries: Map<String, String>
    ): ForecastResponseDto
}
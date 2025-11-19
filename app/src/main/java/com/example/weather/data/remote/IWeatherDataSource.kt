package com.example.weather.data.remote

import com.example.weather.model.Weather

interface IWeatherDataSource {
    suspend fun getWeather(lon: Float, lat: Float): List<Weather>
}
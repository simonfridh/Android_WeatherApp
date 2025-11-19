package com.example.weather.data.repository

import com.example.weather.data.remote.WeatherRemoteDataSource
import com.example.weather.model.Weather
import com.example.weather.model.mapper.toWeather

class WeatherRepository(
    private val remote: WeatherRemoteDataSource
) {

    suspend fun getWeather(lon: Float, lat: Float): List<Weather> {
        return remote.getWeather(lon, lat)
    }
}
package com.example.weather.data.remote

import com.example.weather.data.IWeatherDataSource
import com.example.weather.model.Weather
import com.example.weather.model.mapper.toWeather

class WeatherRemoteDataSource(
    private val api: IWeatherApi
) : IWeatherDataSource {
    override suspend fun getWeather(lon: Float, lat: Float) : List<Weather> {
        val response = api.getWeather(mapOf("lonLat" to "lon/$lon/lat/$lat"))
        return response.timeSeries.map { it.toWeather() }
    }
}
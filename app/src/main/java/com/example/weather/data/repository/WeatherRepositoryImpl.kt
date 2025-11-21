package com.example.weather.data.repository

import com.example.weather.data.mapper.toForecast
import com.example.weather.data.remote.IWeatherApi
import com.example.weather.domain.models.Forecast
import com.example.weather.domain.repository.IWeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: IWeatherApi
) : IWeatherRepository {

    override suspend fun getForecast(lon: Float, lat: Float) : Forecast {
        val response = api.getForecast("lon/$lon/lat/$lat")
        return response.toForecast()
    }

}


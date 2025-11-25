package com.example.weather.data.repository

import com.example.weather.data.local.converter.ForecastConverter
import com.example.weather.data.local.dao.IForecastDao
import com.example.weather.data.mapper.toForecast
import com.example.weather.data.remote.IWeatherApi
import com.example.weather.domain.models.Forecast
import com.example.weather.domain.repository.IWeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: IWeatherApi,
    private val dao: IForecastDao
) : IWeatherRepository {


    override suspend fun getForecastRemote(lon: Float, lat: Float): Result<Forecast> {
        try {
            val response = api.getForecast("lon/$lon/lat/$lat")
            return Result.success(response.toForecast())
        }
        catch(e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getForecastLocal(lon: Float, lat: Float): Forecast? {
        val response = dao.getLatestForecast(lon, lat) ?: return null
        return ForecastConverter.toForecast(response.forecastJson)
    }
}


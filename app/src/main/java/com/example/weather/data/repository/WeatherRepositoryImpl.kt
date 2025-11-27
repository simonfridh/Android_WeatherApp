package com.example.weather.data.repository

import android.util.Log
import com.example.weather.data.local.converter.ForecastConverter
import com.example.weather.data.local.dao.IForecastDao
import com.example.weather.data.local.entity.ForecastEntity
import com.example.weather.data.remote.placename.IPlacenameApi
import com.example.weather.data.remote.placename.mapper.toPlacename
import com.example.weather.data.remote.weatherapi.mapper.toForecast
import com.example.weather.data.remote.weatherapi.IWeatherApi
import com.example.weather.domain.models.forecast.Forecast
import com.example.weather.domain.models.placename.Placename
import com.example.weather.domain.repository.IWeatherRepository
import java.time.LocalDateTime
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: IWeatherApi,
    private val placenameApi: IPlacenameApi,
    private val dao: IForecastDao
) : IWeatherRepository {


    override suspend fun getForecastRemote(lon: Float, lat: Float): Result<Forecast> {
        try {
            Log.d("API_CALL", "Api call made")
            val response = weatherApi.getForecast(longitude = lon, latitude = lat)
            return Result.success(response.toForecast())
        }
        catch(e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getForecastLocal(name: String): Result<Forecast> {
        try {
            val response = dao.getLatestForecast(name) ?: return Result.failure(Exception("No local forecast found"))
            val forecast = ForecastConverter.toForecast(response.forecastJson)

            if(forecast.currentWeather.time.plusDays(1).isBefore(LocalDateTime.now())) return Result.failure(Exception("No recent forecast data saved"))
            return Result.success(forecast)
        }
        catch(e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun saveForecastToLocal(name: String, forecast: Forecast) {
        dao.insert(ForecastEntity(
            name = name,
            forecastJson = ForecastConverter.fromForecast(forecast))
        )
    }

    override suspend fun getPlacenameRemote(name: String): Result<Placename> {
        try {
            val response = placenameApi.getPlacename(name)
            return Result.success(response.toPlacename())
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }
}


package com.example.weather.data.remote

import com.example.weather.data.remote.mapper.toForecast
import com.example.weather.model.Forecast

class WeatherRemote() {
    companion object {
        suspend fun getForecast(lon: Float, lat: Float): Forecast {
            val response = RetrofitInstance.api.getForecast("lon/$lon/lat/$lat")
            return response.toForecast()
        }
    }
}

/*
Här är gamla versionen som inte använde statisk metod

class WeatherRemote(
    private val api: IWeatherApi
) : IWeatherDataSource {
    override suspend fun getForecast(lon: Float, lat: Float) : Forecast {
        val response = api.getForecast("lon/$lon/lat/$lat")
        return response.toForecast()
    }
}
 */
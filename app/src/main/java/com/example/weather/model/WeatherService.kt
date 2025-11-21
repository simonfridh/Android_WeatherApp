package com.example.weather.model

import com.example.weather.data.remote.WeatherRemote
import com.example.weather.model.weathericon.WeatherIcon

class WeatherService {
    companion object {
        suspend fun getRemoteForecast(longitude: Float, latitude: Float): Forecast {
            return WeatherRemote.getForecast(lon = longitude, lat = latitude)
        }

    }
}
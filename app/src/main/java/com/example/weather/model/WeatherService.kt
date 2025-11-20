package com.example.weather.model

import com.example.weather.data.remote.WeatherRemote

class WeatherService {
    companion object {

        suspend fun getRemoteForecast(longitude: Float, latitude: Float): Forecast {
            return WeatherRemote.getForecast(lon = longitude, lat = latitude)
        }

    }
}
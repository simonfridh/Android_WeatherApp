package com.example.weather.data

import com.example.weather.model.Forecast

//TODO används inte för tillfället. Ta bort om inte används
interface IWeatherDataSource {
    suspend fun getForecast(lon: Float, lat: Float): Forecast
}
package com.example.weather

import com.example.weather.data.remote.WeatherRemote
import com.example.weather.model.WeatherService
import kotlinx.coroutines.runBlocking
import kotlin.test.Test


class WeatherApiTest {

    @Test
    fun testApiCall() = runBlocking {
        val forecast = WeatherService.getRemoteForecast(14.333f,60.383f)
        println("Forecast:\n----------")
        println("Longitude: " + forecast.longitude + ", Latitude: " + forecast.latitude)

        println("\nCurrent Weather\n----------")
        val weather = forecast.currentWeather
        println("Current temperature: " + weather.temperature + "°C")


        println("\nHourly Weather (Next 24h)\n----------")
        for (weather in forecast.hourlyWeather) {
            println("" + weather.time.hour + ":00" + ": " + weather.temperature + "°C")
        }

        println("\nDaily Weather\n----------")
        for (weather in forecast.dailyWeather) {
            println("" + weather.time.dayOfWeek + " " +"(" + weather.time.dayOfMonth +
                    " " + weather.time.month + " " + weather.time.year + ")" +
                    ": max=" + weather.maxTemperature + "°C" +
                    ", min=" + weather.minTemperature + "°C"
            )
        }
    }
}
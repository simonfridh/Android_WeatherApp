package com.example.weather

import com.example.weather.data.remote.IWeatherApi
import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.Test

class WeatherApiTest {

    /**
     *  Manually create an api to do tests without HILT.
     *  (Go to AppModule if you want to see what HILT currently uses)
     * */
    @Test
    fun testApiCall() = runBlocking {
        //Create api and repo manually
        val api = Retrofit.Builder()
            .baseUrl("https://maceo.sth.kth.se/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IWeatherApi::class.java)
        val repository: IWeatherRepository = WeatherRepositoryImpl(api)

        //TEST
        val forecast = repository.getForecast(14.333f,60.383f)

        //PRINT
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
            println("" + weather.time.dayOfWeek + " " + "(" + weather.time.dayOfMonth +
                    " " + weather.time.month + " " + weather.time.year + ")" + ": max=" +
                    weather.maxTemperature + "°C" + ", min=" + weather.minTemperature + "°C" ) }
    }
}
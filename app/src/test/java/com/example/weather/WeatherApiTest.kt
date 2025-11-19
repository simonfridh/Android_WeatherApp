package com.example.weather

import com.example.weather.data.remote.RetrofitInstance
import com.example.weather.data.remote.WeatherRemoteDataSource
import kotlinx.coroutines.runBlocking
import kotlin.test.DefaultAsserter.assertNotNull
import kotlin.test.DefaultAsserter.assertTrue
import kotlin.test.Test


class WeatherApiTest {

    @Test
    fun testWeatherApiCall_returnsData() = runBlocking {
        val remote = WeatherRemoteDataSource(RetrofitInstance.api)

        // Call the API
        val weatherList = remote.getWeather(14.333f, 60.383f)

        weatherList.forEach { println(it) }

        assertNotNull("List should contain entries", weatherList)

        println("First weather entry ${weatherList.first()}")
        println("Test passed: received ${weatherList.size} weather entries")
    }
}
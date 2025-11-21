package com.example.weather.model

import com.example.weather.model.weathericon.WeatherIcon
import java.time.LocalDateTime

sealed class Weather{
    abstract val time: LocalDateTime
    abstract val weatherIcon: WeatherIcon

    data class Current(
        override val time: LocalDateTime,
        override val weatherIcon: WeatherIcon,
        val temperature: Float
    ) : Weather()

    data class Hourly(
        override val time: LocalDateTime,
        override val weatherIcon: WeatherIcon,
        val temperature: Float
    ) : Weather()

    data class Daily(
        override val time: LocalDateTime,
        override val weatherIcon: WeatherIcon,
        val maxTemperature: Float,
        val minTemperature: Float
    ) : Weather()
}




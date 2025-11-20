package com.example.weather.model

sealed class Weather{
    abstract val time: String
    abstract val weatherCode: Int

    data class Current(
        override val time: String,
        override val weatherCode: Int,
        val temperature: Float
    ) : Weather()

    data class Hourly(
        override val time: String,
        override val weatherCode: Int,
        val temperature: Float
    ) : Weather()

    data class Daily(
        override val time: String,
        override val weatherCode: Int,
        val maxTemperature: Float,
        val minTemperature: Float
    ) : Weather()
}




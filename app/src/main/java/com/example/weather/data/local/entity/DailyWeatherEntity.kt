package com.example.weather.data.local.entity

import androidx.room.Entity
import com.example.weather.domain.models.WeatherIcon
import java.time.LocalDateTime

@Entity(
    tableName = "daily",
    primaryKeys = ["date", "longitude", "latitude"]
)
data class DailyWeatherEntity(
    val date: LocalDateTime,
    val longitude: Double,
    val latitude: Double,
    val weatherIcon: Int,
    val maxTemp: Float,
    val minTemp: Float
)

/*
  data class Daily(
        override val time: LocalDateTime,
        override val weatherIcon: WeatherIcon,
        val maxTemperature: Float,
        val minTemperature: Float
    ) : Weather()
}
 */
package com.example.weather.data.remote.weatherapi.mapper

import com.example.weather.data.remote.weatherapi.dto.ForecastResponseDto
import com.example.weather.domain.models.forecast.Forecast
import com.example.weather.domain.models.forecast.Weather
import com.example.weather.domain.models.forecast.WeatherIcon
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun ForecastResponseDto.toForecast(): Forecast {
    //check if any fields are missing
    if(longitude == null || latitude == null || current == null || hourly == null || daily == null){
        throw Exception("No valid forecast received")
    }

    //calculate how many hours to add to time based on timezone
    val localZone = ZoneId.systemDefault()
    val offset = localZone.rules.getOffset(LocalDateTime.now())
    val timeZoneHourOffset = offset.totalSeconds / 3600L

    //parse weather data
    val currentWeather = Weather.Current(
        time = LocalDateTime.parse(
            current.time,
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        ).plusHours(timeZoneHourOffset),
        weatherIcon = WeatherIcon.iconFromWeatherCode(current.weather_code),
        temperature = current.temperature_2m.toFloat()
    )

    val hourlyWeather = mutableListOf<Weather.Hourly>()
    for(i in 0 until hourly.time.size) {
        hourlyWeather.add(
            Weather.Hourly(
                time = LocalDateTime.parse(
                    hourly.time[i],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
                ).plusHours(timeZoneHourOffset),
                weatherIcon = WeatherIcon.iconFromWeatherCode(hourly.weather_code[i]),
                temperature = hourly.temperature_2m[i].toFloat()
            )
        )
    }

    val dailyWeather = mutableListOf<Weather.Daily>()
    for(i in 0 until daily.time.size) {
        dailyWeather.add(
            Weather.Daily(
                time = LocalDateTime.parse(
                    daily.time[i] + "T00:00",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
                ),
                weatherIcon = WeatherIcon.iconFromWeatherCode(daily.weather_code[i]),
                maxTemperature = daily.temperature_2m_max[i].toFloat(),
                minTemperature = daily.temperature_2m_min[i].toFloat()
            )
        )
    }

    return Forecast(
        longitude = longitude.toFloat(),
        latitude = latitude.toFloat(),
        currentWeather = currentWeather,
        hourlyWeather = hourlyWeather,
        dailyWeather = dailyWeather
    )
}
package com.example.weather.data.remote.mapper


import com.example.weather.data.remote.dto.ForecastResponseDto
import com.example.weather.data.remote.dto.Parameter
import com.example.weather.model.Forecast
import com.example.weather.model.Weather
import com.example.weather.model.weathericon.WeatherIcon
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.iterator

/**
 * TODO vi måste skriva om hela den här från scratch när vi byter API-länk
 */
fun ForecastResponseDto.toForecast(): Forecast {
    val longitude = geometry.coordinates.firstOrNull()?.getOrNull(0)?.toFloat() ?: 0f
    val latitude = geometry.coordinates.firstOrNull()?.getOrNull(1)?.toFloat() ?: 0f

    val currentTsValues = timeSeries.first()
    val currentWeather = Weather.Current(
        time = LocalDateTime.parse(currentTsValues.validTime, DateTimeFormatter.ISO_DATE_TIME),
        weatherIcon = WeatherIcon.iconFromWeatherCode(currentTsValues.parameters.valueOfInt("Wsymb2")) ,
        temperature = currentTsValues.parameters.valueOfFloat("t")
    )

    //next 24 hours
    val hourlyWeather = mutableListOf<Weather.Hourly>()
    val hourlyTSValues = timeSeries.take(24)
    for (tsValues in hourlyTSValues) {
        hourlyWeather.add(
            Weather.Hourly(
                time = LocalDateTime.parse(tsValues.validTime, DateTimeFormatter.ISO_DATE_TIME),
                weatherIcon = WeatherIcon.iconFromWeatherCode(tsValues.parameters.valueOfInt("Wsymb2")) ,
                temperature = tsValues.parameters.valueOfFloat("t")
            )
        )
    }

    //daily Weather. using group by date and map
    val dailyWeather = mutableListOf<Weather.Daily>()
    val dailyTsValues = timeSeries.groupBy { it.validTime.substring(0, 10) } //creates groups by unique day
    for (dayGroup in dailyTsValues) {
        val firstInGroupValues = dayGroup.value.first()
        dailyWeather.add(
            Weather.Daily(
                time = LocalDateTime.parse(firstInGroupValues.validTime, DateTimeFormatter.ISO_DATE_TIME),
                weatherIcon = WeatherIcon.iconFromWeatherCode(firstInGroupValues.parameters.valueOfInt("Wsymb2")) ,
                maxTemperature = firstInGroupValues.parameters.valueOfFloat("t"),
                minTemperature = firstInGroupValues.parameters.valueOfFloat("t")
            )
        )
    }

    return Forecast(
        longitude = longitude,
        latitude = latitude,
        currentWeather = currentWeather,
        hourlyWeather = hourlyWeather,
        dailyWeather = dailyWeather
    )
}

private fun List<Parameter>.valueOfFloat(name: String): Float {
    return firstOrNull { it.name == name }?.values?.firstOrNull()?.toFloat() ?: 0f
}
private fun List<Parameter>.valueOfInt(name: String): Int {
    return firstOrNull { it.name == name }?.values?.firstOrNull()?.toInt() ?: 0
}








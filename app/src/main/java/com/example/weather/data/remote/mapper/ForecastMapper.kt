package com.example.weather.data.remote.mapper


import com.example.weather.data.remote.dto.ForecastResponseDto
import com.example.weather.data.remote.dto.Parameter
import com.example.weather.model.Forecast
import com.example.weather.model.Weather
import kotlin.collections.iterator

/**
 * TODO vi måste skriva om hela den här från scratch när vi byter API-länk
 */
fun ForecastResponseDto.toForecast(): Forecast {
    val longitude = geometry.coordinates.firstOrNull()?.getOrNull(0)?.toFloat() ?: 0f
    val latitude = geometry.coordinates.firstOrNull()?.getOrNull(1)?.toFloat() ?: 0f

    val currentTS = timeSeries.first()
    val currentWeather = Weather.Current(
        time = currentTS.validTime,
        weatherCode = currentTS.parameters.valueOfInt("Wsymb2"),
        temperature = currentTS.parameters.valueOfFloat("t")
    )

    //next 24 hours
    val hourlyWeather = mutableListOf<Weather>()
    val hourlyTS = timeSeries.take(24)
    for (ts in hourlyTS) {
        hourlyWeather.add(
            Weather.Hourly(
                time = ts.validTime,
                weatherCode = ts.parameters.valueOfInt("Wsymb2"),
                temperature = ts.parameters.valueOfFloat("t")
            )
        )
    }

    //daily Weather. using group by date and map
    val dailyWeather = mutableListOf<Weather>()
    val dailyTs = timeSeries.groupBy { it.validTime.substring(0, 10) } //creates one list per unique day
    for (dayGroup in dailyTs) {
        val first = dayGroup.value.first()
        dailyWeather.add(
            Weather.Daily(
                time = first.validTime,
                weatherCode = first.parameters.valueOfInt("Wsymb2"),
                maxTemperature = first.parameters.valueOfFloat("t"),
                minTemperature = first.parameters.valueOfFloat("t")
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








package com.example.weather.domain.models.forecast

import androidx.annotation.DrawableRes
import com.example.weather.R

sealed class WeatherIcon(
    @field:DrawableRes val icon: Int
) {
    object ClearSky: WeatherIcon(
        icon = R.drawable.clearsky
    )
    object MainlyClear: WeatherIcon(
        icon = R.drawable.mainlyclear
    )
    object PartlyCloudy: WeatherIcon(
        icon = R.drawable.partlycloudy
    )
    object Cloudy: WeatherIcon(
        icon = R.drawable.cloudy
    )
    object Fog: WeatherIcon(
        icon = R.drawable.fog
    )
    object Rain: WeatherIcon(
        icon = R.drawable.rain
    )
    object Thunderstorm: WeatherIcon(
        icon = R.drawable.thunderstorm
    )
    object FreezingRain: WeatherIcon(
        icon = R.drawable.freezingrain
    )
    object Snow: WeatherIcon(
        icon = R.drawable.snowy
    )
    object Rainbow: WeatherIcon(
        icon = R.drawable.rainbow
    )

    companion object {
        fun iconFromWeatherCode(code: Int) : WeatherIcon {
            return when(code) { //uses weather-codes from Open-Meteo.com
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Cloudy
                45,48 -> Fog
                51,53,55,61,63,65,80,81,82 -> Rain
                56,57,66,67 -> FreezingRain
                71,73,75,77,85,86 -> Snow
                95,96,99 -> Thunderstorm

                else -> Rainbow
            }
        }

        fun codeFromWeatherIcon(icon: WeatherIcon) : Int {
            return when(icon) {
                ClearSky -> 0
                MainlyClear -> 1
                PartlyCloudy -> 2
                Cloudy -> 3
                Fog -> 45
                Rain ->  51
                FreezingRain ->  56
                Snow -> 71
                Thunderstorm -> 95

                Rainbow -> -1
            }
        }

    }
}
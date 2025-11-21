package com.example.weather.domain.models

import androidx.annotation.DrawableRes
import com.example.weather.R

sealed class WeatherIcon(
    @field:DrawableRes val icon: Int
) {
    object Sun: WeatherIcon(
        icon = R.drawable.sun
    )

    object CloudSun: WeatherIcon(
        icon = R.drawable.cloudysun
    )

    object Cloud: WeatherIcon(
        icon = R.drawable.cloud
    )

    object Fog: WeatherIcon(
        icon = R.drawable.fog
    )

    object Rain: WeatherIcon(
        icon = R.drawable.rain
    )

    object RainThunder: WeatherIcon(
        icon = R.drawable.rainthunder
    )

    object SnowRain: WeatherIcon(
        icon = R.drawable.snowyrain
    )

    object Snow: WeatherIcon(
        icon = R.drawable.snowy
    )

    object Moon: WeatherIcon(
        icon = R.drawable.moon
    )

    object Rainbow: WeatherIcon(
        icon = R.drawable.rainbow
    )

    companion object {
        fun iconFromWeatherCode(code: Int) : WeatherIcon {
            return when(code) { //uses weather-codes from Open-Meteo.com
                0 -> Sun
                1,2 -> CloudSun
                3 -> Cloud
                45,48 -> Fog
                51,53,55,61,63,65,80,81,82 -> Rain
                56,57,66,67 -> SnowRain
                71,73,75,77,85,86 -> Snow
                95,96,99 -> RainThunder

                else -> Rainbow
            }
        }
    }
}
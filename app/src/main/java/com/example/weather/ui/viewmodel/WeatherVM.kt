package com.example.weather.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.models.Forecast
import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherIcon
import com.example.weather.domain.repository.IWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

interface IWeatherViewModel {
    val weatherState: StateFlow<WeatherState>

    fun getForecast()
}

@HiltViewModel
class WeatherVM @Inject constructor(
    private val repository: IWeatherRepository
): IWeatherViewModel, ViewModel() {
    private val _weatherState = MutableStateFlow(WeatherState())
    override val weatherState: StateFlow<WeatherState>
        get() = _weatherState


    override fun getForecast() {
        viewModelScope.launch {
            _weatherState.value = _weatherState.value.copy(
                forecast = repository.getForecastRemote(14.333f,60.383f) //TODO: Hardcoded Longitude and Latitude
            )
            Log.d("WeatherVM", "Fetched forecast from api")
        }
    }
}

data class WeatherState(
    val forecast: Forecast? = null
)

//Used for Previews. Filled in with values so there is something in the preview (a bit messy)
class FakeVM: IWeatherViewModel {
    override val weatherState: StateFlow<WeatherState>
        get() = MutableStateFlow(WeatherState(
            forecast = Forecast(
                longitude = 14.333f,
                latitude = 60.383f,
                currentWeather = Weather.Current(
                    time = LocalDateTime.parse("2025-11-21T14:00:00Z", DateTimeFormatter.ISO_DATE_TIME),
                    weatherIcon = WeatherIcon.iconFromWeatherCode(1),
                    temperature = 4.9f
                ),
                hourlyWeather = listOf(
                    Weather.Hourly(time = LocalDateTime.parse("2025-11-21T14:00:00Z", DateTimeFormatter.ISO_DATE_TIME),
                        weatherIcon = WeatherIcon.iconFromWeatherCode(1),
                        temperature = 4.9f),
                    Weather.Hourly(time = LocalDateTime.parse("2025-11-21T15:00:00Z", DateTimeFormatter.ISO_DATE_TIME),
                        weatherIcon = WeatherIcon.iconFromWeatherCode(1),
                        temperature = 5.1f)
                ),
                dailyWeather = listOf(
                    Weather.Daily(time = LocalDateTime.parse("2025-11-21T00:00:00Z", DateTimeFormatter.ISO_DATE_TIME),
                        weatherIcon = WeatherIcon.iconFromWeatherCode(1),
                        maxTemperature = 5.1f,
                        minTemperature = 3.7f),
                    Weather.Daily(time = LocalDateTime.parse("2025-11-22T00:00:00Z", DateTimeFormatter.ISO_DATE_TIME),
                        weatherIcon = WeatherIcon.iconFromWeatherCode(0),
                        maxTemperature = 10.2f,
                        minTemperature = 6.3f)
                )
            )
        )).asStateFlow()

    override fun getForecast() {}
}
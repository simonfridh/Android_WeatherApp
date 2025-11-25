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

    fun setLongitudeLatitude(longitude: Float, latitude: Float)
    fun getForecast()
}

@HiltViewModel
class WeatherVM @Inject constructor(
    private val repository: IWeatherRepository
): IWeatherViewModel, ViewModel() {
    private val _weatherState = MutableStateFlow(WeatherState())
    override val weatherState: StateFlow<WeatherState>
        get() = _weatherState

    override fun setLongitudeLatitude(longitude: Float, latitude: Float) {
        val current = _weatherState.value
        if(longitude == current.longitude && latitude == current.latitude) return
        _weatherState.value = _weatherState.value.copy(longitude = longitude, latitude = latitude)
    }

    override fun getForecast() {
        val current = _weatherState.value
        if(current.longitude == null || current.latitude == null) return
        Log.d("API", "API call was made with ${current.longitude}, ${current.latitude}")

        viewModelScope.launch {
            val result = repository.getForecastRemote(current.longitude,current.latitude)
            if(result.isSuccess) {
                Log.d("WeatherVM", "SUCCESS: Fetched forecast from api")
                _weatherState.value = _weatherState.value.copy(forecast = result.getOrNull())
            }
            //TODO Just a temporary solution
            else if(result.isFailure){
                _weatherState.value = _weatherState.value.copy(forecast = null)
                Log.d("WeatherVM", "FAILURE: Fetch from API failed")
            }
        }
    }
}

data class WeatherState(
    val longitude: Float? = null,
    val latitude: Float? = null,
    val forecast: Forecast? = null
)






// Used for Previews. Filled in with values so there is something in the preview
class FakeVM: IWeatherViewModel {
    override val weatherState: StateFlow<WeatherState>
        get() = MutableStateFlow(WeatherState(
            longitude = 14.333f,
            latitude = 60.383f,
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

    override fun setLongitudeLatitude(longitude: Float, latitude: Float) {}
    override fun getForecast() {}
}
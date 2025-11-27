package com.example.weather.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.models.forecast.Forecast
import com.example.weather.domain.models.forecast.Weather
import com.example.weather.domain.models.forecast.WeatherIcon
import com.example.weather.domain.repository.IWeatherRepository
import com.example.weather.domain.util.INetworkChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

interface IWeatherViewModel {
    val weatherState: StateFlow<WeatherState>
    val events: Flow<UiEvent>
    fun getForecast(longitude: Float, latitude: Float)
}

@HiltViewModel
class WeatherVM @Inject constructor(
    private val repository: IWeatherRepository,
    private val networkChecker: INetworkChecker
): IWeatherViewModel, ViewModel() {
    private val _weatherState = MutableStateFlow(WeatherState())
    override val weatherState: StateFlow<WeatherState>
        get() = _weatherState

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    override val events: Flow<UiEvent>
        get() = _events.receiveAsFlow()

    override fun getForecast(longitude: Float, latitude: Float) {
        viewModelScope.launch {
            if(networkChecker.isNetworkAvailable()){ //Internet connection found

                val result = repository.getForecastRemote(longitude,latitude)

                if(result.isSuccess) {
                    val forecast = result.getOrNull()
                    if(forecast != null) {
                        _weatherState.value = _weatherState.value.copy(forecast = forecast)
                        repository.saveForecastToLocal(longitude = longitude, latitude = latitude, forecast = forecast)
                    }
                }
                else if(result.isFailure){
                    Log.d("WeatherVM", "INTERNET FAILURE: ${result.exceptionOrNull()}")
                    _weatherState.value = _weatherState.value.copy(forecast = null)
                    _events.send(UiEvent.ShowPopup("Failed to retrieve forecast. ${result.exceptionOrNull()}"))
                }

            }
            else { // No internet connection found

                val result = repository.getForecastLocal(longitude,latitude)

                if(result.isSuccess) {
                    _weatherState.value = _weatherState.value.copy(forecast = result.getOrNull())
                    _events.send(UiEvent.ShowPopup("No internet connection. Showing old local data"))
                }
                else if(result.isFailure){
                    Log.d("WeatherVM", "LOCAL FAILURE: ${result.exceptionOrNull()}")
                    _weatherState.value = _weatherState.value.copy(forecast = null)
                    _events.send(UiEvent.ShowPopup("No internet connection"))
                }

            }
        }
    }
}

data class WeatherState(
    val forecast: Forecast? = null
)

sealed class UiEvent {
    data class ShowPopup(val msg: String): UiEvent()
}






// Used for Previews. Filled in with values so there is something in the preview
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

    override val events: Flow<UiEvent>
        get() = emptyFlow()

    override fun getForecast(longitude: Float, latitude: Float) {}
}
package com.example.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.models.Forecast
import com.example.weather.domain.repository.IWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
                forecast = repository.getForecast(14.333f,60.383f) //TODO: Hardcoded Longitude and Latitude
            )
        }
    }
}

data class WeatherState(
    val forecast: Forecast? = null
)

class FakeVM: IWeatherViewModel {
    override val weatherState: StateFlow<WeatherState>
        get() = MutableStateFlow(WeatherState()).asStateFlow()

    override fun getForecast() {}
}
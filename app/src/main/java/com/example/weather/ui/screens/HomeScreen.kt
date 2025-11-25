package com.example.weather.ui.screens

import android.content.res.Configuration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.ui.components.CurrentWeatherBox
import com.example.weather.ui.components.HourlyWeatherBox
import com.example.weather.ui.components.LongitudeLatitudeBar
import com.example.weather.ui.components.WeatherList
import com.example.weather.ui.viewmodel.FakeVM
import com.example.weather.ui.viewmodel.IWeatherViewModel

@Composable
fun HomeScreen(
    vm: IWeatherViewModel
) {
    val orientation = LocalConfiguration.current.orientation
    val weatherState by vm.weatherState.collectAsState()

    LaunchedEffect(weatherState.longitude, weatherState.latitude) {
        vm.getForecast()
    }

    //PORTRAIT MODE
    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            WeatherList(
                modifier = Modifier.weight(1f),
                weatherState = weatherState
            ) {
                CurrentWeatherBox(weatherState)
                HourlyWeatherBox(weatherState)
            }
            LongitudeLatitudeBar(onSubmit = vm::setLongitudeLatitude)
        }
    }

    //LANDSCAPE MODE
    if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Column {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                //LEFT SIDE
                Column(
                    modifier = Modifier
                        .weight(0.40f)
                        .fillMaxHeight()
                ) {
                    CurrentWeatherBox(weatherState)
                    HourlyWeatherBox(weatherState)
                }

                //RIGHT SIDE
                Column(
                    modifier = Modifier
                        .weight(0.60f)
                        .fillMaxSize()
                ) {
                    WeatherList(weatherState = weatherState) {}
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LongitudeLatitudeBar(onSubmit = vm::setLongitudeLatitude)
            }
        }


    }
}

@Preview
@Composable
private fun PortraitPreview() {
    HomeScreen(FakeVM())
}

@Preview(widthDp = 915, heightDp = 412)
@Composable
private fun LandscapePreview() {
    HomeScreen(FakeVM())
}
package com.example.weather.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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

    LaunchedEffect(Unit) {
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
            LongitudeLatitudeBar()
        }
    }

    //LANDSCAPE MODE
    if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            //LEFT SIDE
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight(),
                    verticalArrangement = Arrangement.Top
            ) {
                CurrentWeatherBox(weatherState)
                HourlyWeatherBox(weatherState)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    LongitudeLatitudeBar()
                }
            }

            //RIGHT SIDE
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxSize()
            ) {
                WeatherList(weatherState = weatherState) {}
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreviewPortrait() {
    HomeScreen(FakeVM())
}

@Preview(widthDp = 915, heightDp = 412)
@Composable
fun LandscapePreview() {
    HomeScreen(FakeVM())
}
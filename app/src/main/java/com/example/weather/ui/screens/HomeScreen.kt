package com.example.weather.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        Column {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                //LEFT SIDE
                Column(
                    modifier = Modifier
                        .weight(0.4f)
                        .fillMaxHeight()
                ) {
                    CurrentWeatherBox(weatherState)
                    HourlyWeatherBox(weatherState)
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
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LongitudeLatitudeBar()
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
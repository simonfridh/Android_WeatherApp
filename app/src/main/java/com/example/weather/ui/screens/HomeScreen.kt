package com.example.weather.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    //PORTRAIT MODE
    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(weatherState.forecast != null) {
                WeatherList(
                    modifier = Modifier.weight(1f),
                    weatherState = weatherState
                ) {
                    CurrentWeatherBox(weatherState)
                    HourlyWeatherBox(weatherState)
                }
            }
            else{
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "No data",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            LongitudeLatitudeBar(onSubmit = vm::getForecast)
        }



    }

    //LANDSCAPE MODE
    if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Column {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(weatherState.forecast != null){
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
                else {
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "No data",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LongitudeLatitudeBar(onSubmit = vm::getForecast)
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
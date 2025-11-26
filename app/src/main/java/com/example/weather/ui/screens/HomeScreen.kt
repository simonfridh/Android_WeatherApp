package com.example.weather.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.fromColorLong
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.ui.components.CurrentWeatherBox
import com.example.weather.ui.components.HourlyWeatherBox
import com.example.weather.ui.components.LongitudeLatitudeBar
import com.example.weather.ui.components.NoDataBox
import com.example.weather.ui.components.WeatherList
import com.example.weather.ui.viewmodel.FakeVM
import com.example.weather.ui.viewmodel.IWeatherViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    vm: IWeatherViewModel
) {
    val orientation = LocalConfiguration.current.orientation
    val weatherState by vm.weatherState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    //TODO Bara för tester
    LaunchedEffect(Unit){
        vm.getForecast(14.333f,60.383f)
    }

    LaunchedEffect(Unit) {
        scope.launch {
            snackBarHostState.showSnackbar(
                message = "Hello world",
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                SnackbarHost(snackBarHostState)
            }
        }
    ) { padding ->

        //PORTRAIT MODE
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(paddingValues = padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 8.dp)
                ) {
                    if(weatherState.forecast != null) {
                        WeatherList(
                            weatherState = weatherState
                        ) {
                            CurrentWeatherBox(weatherState)
                            Spacer(modifier = Modifier.height(8.dp))
                            HourlyWeatherBox(weatherState)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    else{
                        NoDataBox()
                    }
                }

                LongitudeLatitudeBar(vm::getForecast)
            }
        }

        //LANDSCAPE MODE
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(paddingValues = padding)
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    verticalAlignment = Alignment.Top
                ) {
                    if(weatherState.forecast != null){
                        //LEFT SIDE
                        Column(
                            modifier = Modifier
                                .weight(0.40f)
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            CurrentWeatherBox(
                                weatherState = weatherState,
                                modifier = Modifier
                                    .weight(0.45f)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            HourlyWeatherBox(
                                weatherState = weatherState,
                                modifier = Modifier.weight(0.55f)
                            )
                        }
                        //RIGHT SIDE
                        Column(
                            modifier = Modifier
                                .weight(0.60f)
                                .fillMaxSize()
                                .padding(8.dp,8.dp,8.dp,0.dp)
                        ) {
                            WeatherList(weatherState) {}
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
                    LongitudeLatitudeBar(vm::getForecast)
                }
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